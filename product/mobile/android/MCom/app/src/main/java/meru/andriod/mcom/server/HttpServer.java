package meru.andriod.mcom.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.simple.JSONObject;

import meru.andriod.mcom.ConfigStore;

public class HttpServer {

    private static final HttpServer INSTANCE = new HttpServer("http://10.0.2.2:8080",
                                                              "/ns/e/");

    protected static DefaultHttpClient mHttpClient = new DefaultHttpClient();
    protected String mHost;
    protected String mBaseURL;
    private ConfigStore mConfigStore;

    protected HttpServer(String host, String baseURL) {
        mHost = host;
        mBaseURL = host + baseURL;
    }

    public static HttpServer getInstance() {
        return INSTANCE;
    }

    public void setConfigStore(ConfigStore configStore) {
        mConfigStore = configStore;
    }

    private String getURL(String relativeURL) {
        if (relativeURL.startsWith("/")) {
            return mHost + relativeURL;
        }

        return mBaseURL + relativeURL;
    }

    public String get(String relativeURL) {

        try {

            String url = getURL(relativeURL);
            HttpGet httpGet = new HttpGet(url);
            return invoke(httpGet);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public String post(String relativeURL, JSONObject data) {

        try {

            HttpPost post = new HttpPost(getURL(relativeURL));
            StringEntity strEntity = new StringEntity(data.toString());
            strEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,
                                                     "application/json"));
            post.setEntity(strEntity);

            return invoke(post);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public String post(String relativeURL, List<NameValuePair> nameValuePairs) {

        HttpPost httpPost = new HttpPost(getURL(relativeURL));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            return invoke(httpPost);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String delete(String relativeURL) {

        try {

            mHttpClient.execute(new HttpDelete(getURL(relativeURL)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    /*
     * private void printCookies() {
     * 
     * List<Cookie> cookies = mHttpClient.getCookieStore() .getCookies();
     * 
     * System.out.println("    COOKIE LENGTH "+cookies.size()); for (Cookie
     * cookie : cookies) { System.out.println("#### COOKIE " + cookie.getName()
     * + "  " + cookie.getValue());
     * 
     * }
     * 
     * }
     */

    private String getSessionIdFromConfigStore() {
        String sessionId = mConfigStore.getProperty(ConfigStore.APP_SESSION_ID,
                                                    null);

        return sessionId;
    }

    private Cookie getSessionCookie() {

        CookieStore cookieStore = mHttpClient.getCookieStore();
        List<Cookie> cookies = cookieStore.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName()
                      .equals("S")) {
                return cookie;
            }
        }

        return null;
    }

    private void saveSessionCookieInConfigStore(HttpResponse httpResponse) {

        String sessionId = getSessionIdFromConfigStore();
        if (sessionId != null) {
            return;
        }

        Cookie cookie = getSessionCookie();

        if (cookie != null) {
            mConfigStore.storeProperty(ConfigStore.APP_SESSION_ID,
                                       cookie.getValue());
        }

    }

    private String invoke(HttpUriRequest httpMethod) throws ClientProtocolException,
                                                     IOException {
        InputStream inputStream = null;
        String result = null;
        try {

            //if (getSessionCookie() == null) {
               //requires to be set to all requests 
                String sessionId = getSessionIdFromConfigStore();

                if (sessionId != null) {
                    httpMethod.setHeader("Cookie",
                                         "S=" + sessionId + ";");
                }
            //}

            HttpResponse httpResponse = mHttpClient.execute(httpMethod);
            inputStream = httpResponse.getEntity()
                                      .getContent();

            saveSessionCookieInConfigStore(httpResponse);

            if (httpResponse.getStatusLine()
                            .getStatusCode() != HttpStatus.SC_OK) {
                throw new IOException("Invocation Failed");
            }
            if (inputStream != null) {
                result = convertInputStreamToString(inputStream);
            }
            else {
                result = "Did not work!";
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    // convert inputstream to String
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    // check network connection
    public boolean isConnected() {
        /*
         * ConnectivityManager connMgr = (ConnectivityManager)
         * getSystemService(this.CONNECTIVITY_SERVICE); NetworkInfo networkInfo
         * = connMgr.getActiveNetworkInfo(); if (networkInfo != null &&
         * networkInfo.isConnected()) return true; else return false;
         */
        return false;
    }

}
