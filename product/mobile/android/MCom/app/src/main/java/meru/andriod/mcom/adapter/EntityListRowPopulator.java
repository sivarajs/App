package meru.andriod.mcom.adapter;

import org.json.simple.JSONObject;

import android.view.View;

public interface EntityListRowPopulator {

    
    public void populate(View listRow, JSONObject entity, int position);
   
    public void empty();
}
