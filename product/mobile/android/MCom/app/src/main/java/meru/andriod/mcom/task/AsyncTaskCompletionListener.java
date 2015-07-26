package meru.andriod.mcom.task;

import org.json.simple.JSONObject;

public interface AsyncTaskCompletionListener {

    void onExecutionComplete(JSONObject jsonObject);
    
}
