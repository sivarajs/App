package meru.app.security;

public class AuthToken {

    private String mAKey;
    private String mUser;
    private Long mUserId;
    private String mHome;

    public AuthToken(String aKey, String userName, Long userId, String home) {
        mAKey = aKey;
        mUser = userName;
        mUserId = userId;
        mHome = home;
    }

    public String getAKey() {
        return mAKey;
    }

    public String getUser() {
        return mUser;
    }

    public Long getUserId() {
        return mUserId;
    }

    public String getHome() {
        return mHome;
    }

    public String toString() {
        return mAKey;
    }

}
