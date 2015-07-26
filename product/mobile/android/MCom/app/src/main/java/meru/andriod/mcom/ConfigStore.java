package meru.andriod.mcom;

import android.content.Context;
import android.content.SharedPreferences;

public class ConfigStore {

    public static final String APP_PERSISTENT_FILE = "app.persistentFile";
    public static final String APP_USER_NAME = "app.username";
    public static final String APP_PASSWORD = "app.password";

    public static final String APP_SESSION_ID = "app.sessionId";

    private Context mContext;

    private static ConfigStore INSTANCE;

    private ConfigStore(Context context) {
        mContext = context;
    }

    public static void createInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ConfigStore(context);
        }
    }

    public static ConfigStore getInstance() {
        return INSTANCE;
    }

    public void storeProperty(String name, String value) {

        SharedPreferences settings = mContext.getSharedPreferences(APP_PERSISTENT_FILE,
                                                                   0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(name,
                         value);
        editor.commit();

    }

    public String getProperty(String name, String defaultValue) {

        SharedPreferences settings = mContext.getSharedPreferences(APP_PERSISTENT_FILE,
                                                                  0);
        return settings.getString(name,
                                  defaultValue);

    }

}
