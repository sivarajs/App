package meru.andriod.mcom.task;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.os.AsyncTask;
import meru.andriod.mcom.server.HttpServer;

public class HtttpAsyncGetTask extends AsyncTask<String, String, String> {

    private AsyncTaskCompletionListener listener;

    public HtttpAsyncGetTask(AsyncTaskCompletionListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... urls) {

        String json = HttpServer.getInstance()
                                .get(urls[0]);
        return json;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        System.out.println("############### " + result);
        JSONObject jsonObject = null;
        if (result != null && !result.trim().equals("")) {

            try {
                jsonObject = (JSONObject) new JSONParser().parse(result);
            } catch (ParseException e) {

                throw new RuntimeException(e.getMessage());
            }
        }
        listener.onExecutionComplete(jsonObject);
    }

}
