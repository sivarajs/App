package meru.app.binding.http.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import meru.app.AppContext;
import meru.app.AppRequest;
import meru.app.binding.BindingComponentRegistry;
import meru.app.binding.http.HttpAppRequest;
import meru.app.binding.http.HttpAppSecurity;
import meru.app.binding.http.HttpBindingComponent;
import meru.app.binding.http.exception.ExceptionHandlerFactory;
import meru.app.config.AppConfig;
import meru.app.session.Session;
import meru.app.session.http.HttpSessionManager;
import meru.exception.AppException;
import meru.transaction.TransactionManager;
import meru.ui.faces.EntityDataProvider;
//import kaga.app.session.Session;
//import kaga.app.session.http.HttpSessionManager;

public abstract class AppServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected static int SSL_PORT = -1;
    protected ServletContext mServletContext;

    protected AppConfig mAppConfig;
    protected AppContext mAppContext;
    protected HttpBindingComponent mBindingComponent;
    private TransactionManager mTransactionManager;
    protected EntityDataProvider mEntityDataProvider;

    private HttpAppSecurity mAppSecurity;
    protected HttpSessionManager mSessionManager;

    @Override
    public void init(ServletConfig config) throws ServletException {

        mServletContext = config.getServletContext();

        mTransactionManager = TransactionManager.getInstance();
        mBindingComponent = (HttpBindingComponent) BindingComponentRegistry.getInstance()
                                                                           .getBindingComponent(HttpBindingComponent.TYPE);

        mSessionManager = (HttpSessionManager) mBindingComponent.getSessionManager();
        mAppConfig = mBindingComponent.getAppConfig();
        mAppContext = mBindingComponent.getAppContext();

        mAppSecurity = mBindingComponent.getHttpAppSecurity();

        mEntityDataProvider = mBindingComponent.getEntityDataProvider();

    }

    private void completeRequestProcessing() {
        mTransactionManager.closeSession();
        AppRequest.clear();
    }

    private final String getRequestURL(HttpServletRequest request) {
        return request.getRequestURI();
    }

    protected final HttpAppRequest getAppRequest(HttpServletRequest request,
                                                 HttpServletResponse response) throws UnsupportedEncodingException {

        HttpAppRequest appRequest = getAppRequestInstance(request,
                                                          response);

        Session session = mSessionManager.getSession(request,
                                                     response);

        // ThreadContext.getCurrentContext()
        // .setTimeZone(session.getTimeZone());
        //
        //
        appRequest.setContext(session);
        return appRequest;
    }

    protected HttpAppRequest getAppRequestInstance(HttpServletRequest request,
                                                   HttpServletResponse response) throws UnsupportedEncodingException {
        HttpAppRequest appRequest = new HttpAppRequest(getRequestURL(request),
                                                       request,
                                                       response);

        return appRequest;
    }

    private final boolean preRequestCheck(HttpAppRequest appRequest) throws IOException {

        mAppSecurity.enforce(appRequest);

        return true;
    }

    protected final void doGet(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException,
                                                             IOException {

        try {
            HttpAppRequest appRequest = getAppRequest(request,
                                                      response);
            if (preRequestCheck(appRequest)) {

                get(appRequest);

                if (mTransactionManager.isTransactionActive()) {
                    mTransactionManager.commit();
                }

            }
        } catch (Exception e) {
            handleException(e,
                            request,
                            response);
        } finally {
            completeRequestProcessing();
        }

    }

    protected final void doPost(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException,
                                                              IOException {

        try {
            HttpAppRequest appRequest = getAppRequest(request,
                                                      response);

            if (preRequestCheck(appRequest)) {
                mTransactionManager.begin();
                post(appRequest);
                mTransactionManager.commit();
            }
        } catch (Exception e) {
            handleException(e,
                            request,
                            response);

        } finally {
            completeRequestProcessing();
        }
    }

    protected final void doDelete(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException,
                                                                IOException {

        try {

            HttpAppRequest appRequest = getAppRequest(request,
                                                      response);

            if (preRequestCheck(appRequest)) {

                mTransactionManager.begin();
                delete(appRequest);
                mTransactionManager.commit();
            }
        } catch (Exception e) {
            handleException(e,
                            request,
                            response);
        }

    }

    protected void get(HttpAppRequest appRequest) throws ServletException,
                                                  IOException {

    }

    protected void post(HttpAppRequest appRequest) throws ServletException,
                                                   IOException {

    }

    protected void delete(HttpAppRequest appRequest) throws ServletException,
                                                     IOException {

    }

    private boolean handleException(Exception e,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {

        try {
            if (processException(e,
                                 request,
                                 response)) {
                if (mTransactionManager.isTransactionActive()) {
                    mTransactionManager.rollback();
                }

            }
            else {
                if (mTransactionManager.isTransactionActive()) {
                    mTransactionManager.commit();
                }
            }
        } catch (IOException |
                 SecurityException |
                 IllegalStateException |
                 SystemException |
                 RollbackException |
                 HeuristicMixedException |
                 HeuristicRollbackException ex) {
            ex.printStackTrace();
        }

        return true;

    }

    private boolean processException(Exception e,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws IOException {

        ExceptionHandlerFactory.getExceptionHandler(e.getClass())
                               .handle(e,
                                       request,
                                       response);

        if (e instanceof AppException) {
            return ((AppException) e).isRollbackOnly();
        }
        return true;

    }

}
