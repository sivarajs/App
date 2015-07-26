package meru.application.security.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import app.domain.security.NewUser;
import meru.app.binding.http.HttpAppRequest;
import meru.app.binding.http.servlet.AppServlet;
import meru.app.security.AuthToken;
import meru.application.security.SecurityAppProperty;
import meru.application.security.UserType;
import meru.sys.IOSystem;
import meru.template.TemplateData;
import meru.template.TemplateEngine;
import meru.template.TemplateStringData;

@MultipartConfig
public class AccountServlet extends AppServlet {

    private static final long serialVersionUID = 1L;

    private AccountManager accountManager;
    private String appBaseUrl;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        accountManager = AccountManager.getInstance();
        appBaseUrl = mAppContext.getHttpPort()
                                .getBaseUrl()
                + "/";
        if (mAppConfig.getProperty("app.managed") != null) {
            appBaseUrl += mAppConfig.getProperty("app.managed") + "/";
        }
    }

    @Override
    protected void delete(HttpAppRequest appRequest) throws IOException {
    }

    @Override
    protected void get(HttpAppRequest appRequest) throws ServletException,
                                                  IOException {
        String pathInfo = appRequest.getRequest()
                                    .getPathInfo();
        if (pathInfo.equals("/logout")) {
            mSessionManager.unsetCookie("E",
                                        appRequest.getResponse());
            appRequest.getResponse()
                      .sendRedirect(appBaseUrl
                              + appRequest.getSingleParameter("a"));
        }
    }

    private AuthToken login(HttpAppRequest appRequest) {
        String userName = appRequest.getSingleParameter("email");
        String password = appRequest.getSingleParameter("password");
        String appId = appRequest.getSingleParameter("appId");

        AuthToken authToken = accountManager.authenticate(userName,
                                                          password,
                                                          appId);

        if (authToken.getUserId() != null) {
            mSessionManager.userLoggedIn(authToken,
                                         appRequest.getRequest(),
                                         appRequest.getResponse());
        }

        return authToken;
    }

    private NewUser register(HttpAppRequest appRequest) {

        String name = appRequest.getSingleParameter("name");
        String email = appRequest.getSingleParameter("email");
        String mobile = appRequest.getSingleParameter("mobile");
        String password = appRequest.getSingleParameter("password");
        String rpassword = appRequest.getSingleParameter("rpassword");
        String roleId = appRequest.getSingleParameter("roleId");
        String appId = appRequest.getSingleParameter("appId");

        if (!password.equals(rpassword)) {
            throw new RuntimeException("Passwords do not match");
        }

        NewUser newUser = accountManager.createTempNewUser(name,
                                                           email,
                                                           mobile,
                                                           password,
                                                           Long.parseLong(roleId),
                                                           appId);
        return newUser;
    }

    @Override
    protected void post(HttpAppRequest appRequest) throws ServletException,
                                                   IOException {

        String pathInfo = appRequest.getRequest()
                                    .getPathInfo();
        String referer = appRequest.getRequest()
                                   .getHeader("referer");

        // Mobile
        if (referer == null) {

            try {
                login(appRequest);
                appRequest.getResponse()
                          .setStatus(200);
            } catch (Exception e) {
                e.printStackTrace();
                appRequest.getResponse()
                          .setStatus(500);
            }

        }
        else {

            boolean isAjax = referer != null && !referer.contains("/account/");

            if (pathInfo.equals("/login")) {

                try {
                    AuthToken authToken = login(appRequest);

                    String home = authToken.getHome();

                    if (isAjax) {

                        String type = appRequest.getSingleParameter("t");

                        if (type == null) {
                            type = home;
                        }
                        TemplateStringData templateData = new TemplateStringData(type);
                        sentResponse("login-success.html",
                                     templateData,
                                     appRequest.getResponse()
                                               .getWriter());
                    }
                    else {

                        appRequest.getResponse()
                                  .sendRedirect(authToken.getHome());

                    }

                } catch (Exception e) {

                    e.printStackTrace();

                    if (isAjax) {
                        sentResponse("login-failure.html",
                                     new TemplateStringData(e.getMessage()),
                                     appRequest.getResponse()
                                               .getWriter());
                    }
                    else {
                        redirect(appRequest,
                                 referer,
                                 e.getMessage());
                    }
                }

            }
            else if (pathInfo.equals("/register")) {
                // String name = appRequest.getSingleParameter("name");
                // String email = appRequest.getSingleParameter("email");
                // String mobile = appRequest.getSingleParameter("mobile");
                // String password = appRequest.getSingleParameter("password");
                // String rpassword =
                // appRequest.getSingleParameter("rpassword");
                // String roleId = appRequest.getSingleParameter("roleId");
                // String appId = appRequest.getSingleParameter("appId");

                try {
                    // if (!password.equals(rpassword)) {
                    // throw new RuntimeException("Passwords do not match");
                    // }
                    //
                    // NewUser newUser = accountManager.createTempNewUser(name,
                    // email,
                    // mobile,
                    // password,
                    // Long.parseLong(roleId),
                    // appId);

                    NewUser newUser = register(appRequest);

                    if (isAjax) {

                        String userType = UserType.getUserType(newUser.getEmail())
                                                  .getType();

                        sentResponse("register-success.html",
                                     new TemplateStringData(userType),
                                     appRequest.getResponse()
                                               .getWriter());

                    }
                    else {

                        if (referer.indexOf('?') != -1)
                            referer = referer.substring(0,
                                                        referer.indexOf('?'));

                        if (!referer.endsWith("/")) {
                            referer += "/";
                        }

                        appRequest.getResponse()
                                  .sendRedirect(referer + "confirm.xhtml");

                    }
                } catch (Exception e) {

                    e.printStackTrace();
                    if (isAjax) {
                        sentResponse("register-failure.html",
                                     new TemplateStringData(e.getMessage()),
                                     appRequest.getResponse()
                                               .getWriter());
                    }
                    else {

                        redirect(appRequest,
                                 referer,
                                 e.getMessage());
                    }
                }
            }
        }
    }

    private void sentResponse(String fileName,
                              TemplateData templateData,
                              PrintWriter writer) {
        String template = IOSystem.read(mAppContext.getInputStream(SecurityAppProperty.TEMPLATE_DIR_ACCOUNT
                + fileName));
        String message = TemplateEngine.getText(template,
                                                templateData);
        writer.write(message);
    }

    private void redirect(HttpAppRequest appRequest,
                          String url,
                          String errorMsg) throws IOException {

        if (url.contains("&i=")) {
            url = url.substring(0,
                                url.indexOf("&i="));
        }

        appRequest.getResponse()
                  .sendRedirect(url + "&i=" + errorMsg);

    }
}
