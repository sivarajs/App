package meru.application.security.lifecycle;

import app.domain.comm.SendEmail;
import app.domain.comm.SendSMS;
import app.domain.security.NewUser;
import app.domain.security.SecuredApplication;
import app.domain.security.User;
import meru.app.service.ServiceManager;
import meru.application.security.SecurityAppProperty;
import meru.application.security.account.AccountManager;
import meru.exception.RedirectException;
import meru.exception.SystemException;
import meru.messaging.MessageSender;
import meru.persistence.EntityQuery;
import meru.sys.IOSystem;
import meru.sys.SystemCalendar;
import meru.template.TemplateEngine;
import meru.template.TemplateMultiData;

public class NewUserLifeCycle extends AccountLifeCycle<NewUser> {

    private String appRoot;
  private MessageSender messageSender;
  private SystemCalendar systemCalendar;

  public NewUserLifeCycle() throws Exception {

 //   messageSender = new HttpMessageSender();
    systemCalendar = SystemCalendar.getInstance();
    appRoot= "http://localhost:8080/";
  }

  @Override
  public void init() {
    new AccountManager(appContext,
                       appEngine);
    messageSender = serviceManager.getService(ServiceManager.MESSAGE_SENDER, MessageSender.class);
  }

  @Override
  public NewUser preGet(Class<NewUser> entityClass,
                        Object id) {

    String accessToken = (String) id;
    if (accessToken.startsWith("email/")) {
      accessToken = accessToken.substring(6);
    }

    EntityQuery<NewUser> entityQuery = appEngine.createQuery(NewUser.class);
    entityQuery.addQueryParameter("emailAccessToken",
                                  accessToken);

    NewUser newUser = appEngine.getFirst(entityQuery);

    if (newUser == null) {

      throw new SystemException("Illegal Registration Confirmation : " + id);
    }

    SecuredApplication app = appEngine.get(SecuredApplication.class, newUser.getAppId()); 
    
    String appRoot = app.getRoot();//newUser.getPrimaryRole().getHome();

    if (userExists(newUser.getEmail(),
                   newUser.getAppId())) {
      throw new RedirectException(appRoot + "/welcome.xhtml");
    }

    User user = createUser(newUser);
    appEngine.remove(NewUser.class,
                     newUser.getId());

    sendWelcomeMail(user);

    messageSender.send(this.appRoot+appRoot + "/e/RegisteredNewUser?new",
                       "{\"id\":\"" + user.getId() + "\",\"email\":\""
                           + user.getEmail() + "\",\"mobile\":\""
                           + user.getMobile() + "\"}",
                       "application/json");

    throw new RedirectException(appRoot + "/welcome.xhtml");

  }

  @Override
  public boolean preCreate(NewUser newUser) {
    return true;
  }

  @Override
  public Object postCreate(NewUser newUser) {

    if (newUser.getEmail() != null) {
      sendRegistrationConfirmationMail(newUser);
    }

    if (newUser.getMobile() != null) {
      sendRegistrationConfirmationSMS(newUser);
    }
    return null;
  }

  private void sendWelcomeMail(User user) {

    String template = IOSystem.read(appContext.getInputStream(SecurityAppProperty.TEMPLATE_DIR_MAIL
        + "WelcomeMail.html"));

    String message = TemplateEngine.getText(template,
                                            user);

    SendEmail email = new SendEmail();
    email.setTos(user.getEmail());
    email.setSubject("Welcome To Dewbag.com");
    email.setMessage(message);
    email.setContentType("text/html");
    email.setReference("reg: " + user.getAccessToken());
    email.setSentOn(SystemCalendar.getInstance()
                                  .getUTCCalendar());
    email.setState("N");
   // email.setState(MessageState.NEW.getState());

    appEngine.save(email);
  }

  private void sendRegistrationConfirmationMail(NewUser newUser) {

    String template = IOSystem.read(appContext.getInputStream(SecurityAppProperty.TEMPLATE_DIR_MAIL
        + "ConfirmRegistration.html"));
    TemplateMultiData templateData = new TemplateMultiData();
    templateData.addObject("user",
                           newUser);
    templateData.addObject("appContext",
                           appContext);
    String message = TemplateEngine.getText(template,
                                            templateData);

    SendEmail email = new SendEmail();
    email.setTos(newUser.getEmail());
    email.setSubject("Confirm Registration");
    email.setMessage(message);
    email.setContentType("text/html");
    email.setReference("user-reg: " + newUser.getEmailAccessToken());
    email.setSentOn(SystemCalendar.getInstance()
                                  .getUTCCalendar());
    email.setState("N");

    appEngine.save(email);
  }

  private void sendRegistrationConfirmationSMS(NewUser newUser) {

    String text = IOSystem.read(appContext.getInputStream(SecurityAppProperty.TEMPLATE_DIR_SMS
        + "ConfirmationRegistration.txt"));
    text = text.replace("#{code}",
                        newUser.getMobileAccessToken());

    SendSMS sms = new SendSMS();
    sms.setNumber(newUser.getMobile());
    sms.setMessage(text);
    sms.setReference("so:" + newUser.getId());
    //sms.setState(MessageState.NEW.getState());
    sms.setSentOn(systemCalendar.getUTCCalendar());
    appEngine.save(sms);
  }

  public static class UserData {
    private NewUser user;
    private String baseUrl;

    public UserData(NewUser user, String baseUrl) {
      this.user = user;
      this.baseUrl = baseUrl;
    }

    public NewUser getUser() {
      return user;
    }

    public String getBaseUrl() {
      return baseUrl;
    }

  }

}
