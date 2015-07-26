package meru.app.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import meru.sys.uid.UIDGenerator;

public class SessionManager {

	/*
	 * private static final Logger mLogger =
	 * LoggerFactory.getLogger(SessionManager.class.getPackage() .getName());
	 */
	protected final static int COOKIE_MAX_AGE = 3 * 24 * 60 * 60;

	protected Map<String, Session> mSessionMap;
	protected UIDGenerator mUIDGenerator;

	private List<SessionListener> mSessionListeners;

	public SessionManager() {
		mSessionMap = new ConcurrentHashMap<String, Session>();

		mSessionListeners = new ArrayList<SessionListener>(1);
	}
	
	public void setUIDGenerator(UIDGenerator uidGenerator) {
        mUIDGenerator = uidGenerator;
    }
	
	public void addSessionListener(SessionListener sessionListener) {
		mSessionListeners.add(sessionListener);
	}

	protected void userLoggedIn(Session session) {
		for (SessionListener sessionListener : mSessionListeners) {
			sessionListener.userLoggedIn(session);
		}
	}

	protected void userLoggedOut(Session session) {
		for (SessionListener sessionListener : mSessionListeners) {
			sessionListener.userLoggedOut(session);
		}
	}

}
