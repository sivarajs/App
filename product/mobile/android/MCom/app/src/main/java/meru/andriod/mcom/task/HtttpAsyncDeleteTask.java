package meru.andriod.mcom.task;

import org.json.simple.JSONObject;

import android.os.AsyncTask;
import meru.andriod.mcom.server.HttpServer;

public class HtttpAsyncDeleteTask extends AsyncTask<String, String, String> {

    private AsyncTaskCompletionListener listener;

    public HtttpAsyncDeleteTask(AsyncTaskCompletionListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... urls) {

        String json = HttpServer.getInstance()
                                .delete(urls[0]);
        return json;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        JSONObject jsonObject = null;
        /*try {
            jsonObject = (JSONObject) new JSONParser().parse(result);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }*/
        listener.onExecutionComplete(jsonObject);
    }

}
