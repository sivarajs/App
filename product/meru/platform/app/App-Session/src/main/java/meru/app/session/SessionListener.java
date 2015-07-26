package meru.app.session;


public interface SessionListener {

    void sessionInitialized(Session session);
    void userLoggedIn(Session session);
    void userLoggedOut(Session session);
}
