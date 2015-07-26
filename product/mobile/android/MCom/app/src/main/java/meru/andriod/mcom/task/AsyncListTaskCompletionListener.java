package meru.andriod.mcom.task;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AsyncListTaskCompletionListener implements AsyncTaskCompletionListener {

    private List<JSONObject> entities;
    
    public AsyncListTaskCompletionListener(List<JSONObject> entities) {
        this.entities = entities;
    }
    
    @Override
    public void onExecutionComplete(JSONObject jsonObject) {
        JSONArray jsonArray = (JSONArray) jsonObject.get("items");

        int length = jsonArray.size();
        for (int i = 0; i < length; i++) {
            JSONObject entity = (JSONObject) jsonArray.get(i);
            entities.add(entity);
        }
    }

}
