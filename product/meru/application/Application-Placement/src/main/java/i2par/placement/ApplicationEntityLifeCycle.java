package i2par.placement;

import i2par.campus.Campus;
import meru.app.engine.entity.AbstractEntityLifeCycle;
import meru.app.service.account.AccountService;
import meru.application.security.Password;
import meru.persistence.AttributeOperator;
import meru.persistence.EntityQuery;
import meru.sys.SystemCalendar;
import app.domain.Property;
import app.domain.PropertyGroup;
import app.domain.SequenceId;
import app.domain.comm.SendEmail;
import app.domain.security.Role;
import app.domain.security.User;

public abstract class ApplicationEntityLifeCycle<T> extends AbstractEntityLifeCycle<T> {

    private static final byte DOCUMENT_ID_LENGTH = 10;
    
    private AccountService accountService;
    private String appId;
    
    @Override
    public void init() {
        accountService = serviceManager.getService(AccountService.SERVICE_ACCOUNT, AccountService.class);
        appId = appConfig.getProperty("app.id");
    }

    protected final <E> EntityQuery<E> createEntityQuery(Class<E> entityClass) {
        return appEngine.createQuery(entityClass);
    }

    protected final <E> EntityQuery<E> createEntityQuery(Class<E> entityClass,
                                                         String attrName,
                                                         Object attrValue) {

        EntityQuery<E> entityQuery = createEntityQuery(entityClass);
        entityQuery.addQueryParameter(attrName,
                                      AttributeOperator.EQUALS,
                                      attrValue);
        return entityQuery;
    }
    
    protected void createUser(String userId, long roleId, String info) {

        String password = Password.encryptPassword("welcome");

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("{\"name\":\"")
                  .append(userId)
                  .append("\", ");
        strBuilder.append("\"password\":\"")
                  .append(password)
                  .append("\", ");
        strBuilder.append("\"info\":\"")
                  .append(info)
                  .append("\", ");
        strBuilder.append("\"appId\":\"")
                  .append(appId)
                  .append("\", ");
        strBuilder.append("\"primaryRole\": {\"id\":\"")
                  .append(roleId)
                  .append("\"}}");

        accountService.createUser(strBuilder.toString());
    }
    
    protected Property getProperty(Long ownerId,
                                   String category,
                                   String prefix,
                                   String name) {

        EntityQuery<Property> entityQuery = appEngine.createQuery(Property.class);
        entityQuery.addQueryParameter("ownerId",
                                      ownerId);
        entityQuery.addQueryParameter("category",
                                      category);
        entityQuery.addQueryParameter("prefix",
                                      prefix);
        entityQuery.addQueryParameter("name",
                                      name);
        Property property = appEngine.getFirst(entityQuery);

        return property;
    }

    protected PropertyGroup getPropertyGroup(String name,
                                             String value) {

        EntityQuery<PropertyGroup> entityQuery = appEngine.createQuery(PropertyGroup.class);
        entityQuery.addQueryParameter("name",
                                      name);
        entityQuery.addQueryParameter("value",
                                      value);
        PropertyGroup propertyGroup = appEngine.getFirst(entityQuery);

        return propertyGroup;
    }

    protected long getSequenceId(long entityId,
                                 String name) {

        EntityQuery<SequenceId> entityQuery = appEngine.createQuery(SequenceId.class);
        entityQuery.addQueryParameter("name",
                                      name);
        SequenceId sequenceId = appEngine.getFirst(entityQuery);

        long seqId = 1L;

        if (sequenceId == null) {
            sequenceId = new SequenceId();
            sequenceId.setEntityId(Long.valueOf(entityId));
            sequenceId.setName(name);
            sequenceId.setValue(seqId);
        }
        else if (sequenceId.getValue() != null) {
            seqId = sequenceId.getValue()
                              .longValue() + 1L;
        }

        sequenceId.setValue(seqId);

        appEngine.save(sequenceId);
        return seqId;
    }

    private static String getDocumentNumber(int totalDigits,
                                            long id) {

        String idStr = String.valueOf(id);
        StringBuilder strBuilder = new StringBuilder();

        int length = idStr.length();

        for (int i = 0; i < totalDigits - length; ++i) {
            strBuilder.append("0");
        }
        strBuilder.append(idStr);
        return strBuilder.toString();
    }

    protected static String getDocumentId(String idFormat,
                                          Campus campus,
                                          long id) {

        String idStr = getDocumentNumber(DOCUMENT_ID_LENGTH,
                                         id);
        StringBuilder strBuilder = new StringBuilder(idFormat);
        int index = strBuilder.indexOf("${Campus.documentPrefix}");

        strBuilder.replace(index,
                           index + "${Campus.documentPrefix}".length(),
                           campus.getCode());

        index = strBuilder.indexOf("${Number}");

        strBuilder.replace(index,
                           index + "${Number}".length(),
                           idStr);

        return strBuilder.toString();
    }

    protected static SendEmail createSendEmail(String to,
                                               String subject,
                                               String message) {
        SendEmail sendMail = new SendEmail();
        sendMail.setTos(to);
        sendMail.setSubject(subject);
        sendMail.setMessage(message);
        sendMail.setContentType("text/html");
        sendMail.setState("N");
        sendMail.setSentOn(SystemCalendar.getInstance()
                                         .getUTCCalendar());

        return sendMail;
    }

    protected User createUser(String name,
                              String roleName,
                              long entityId) {

        User user = new User();
        user.setName(name);
        user.setPassword("welcome1");
        EntityQuery<Role> roleQuery = appEngine.createQuery(Role.class);
        roleQuery.addQueryParameter("name",
                                    roleName);

        Role role = appEngine.getFirst(roleQuery);

        user.setPrimaryRole(role);
      //  user.setEntityId(entityId);

        appEngine.save(user);

        return user;
    }
}
