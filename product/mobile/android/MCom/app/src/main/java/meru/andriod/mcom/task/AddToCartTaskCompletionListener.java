package meru.andriod.mcom.task;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AddToCartTaskCompletionListener implements AsyncTaskCompletionListener {

    private List<JSONObject> entities;
    
    public AddToCartTaskCompletionListener(List<JSONObject> entities) {
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
