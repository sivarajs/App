package meru.andriod.mcom.task;

import java.util.List;

import org.apache.http.NameValuePair;
import org.json.simple.JSONObject;

import meru.andriod.mcom.server.HttpServer;

public class HtttpAsyncPostTask extends HtttpAsyncGetTask  {

    private Object entity;

    public HtttpAsyncPostTask(AsyncTaskCompletionListener listener,
                              Object entity) {
        super(listener);
        this.entity = entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected String doInBackground(String... urls) {

        HttpServer httpServer = HttpServer.getInstance();

        String json = null;

        if (entity instanceof JSONObject) {
            json = httpServer.post(urls[0],
                                   (JSONObject) entity);
        }
        else if (entity instanceof List) {
            json = httpServer.post(urls[0],
                                   (List<NameValuePair>) entity);
        }
        else {
            throw new RuntimeException("Unsupported object : "
                    + entity.getClass());
        }
        return json;
    }

}
