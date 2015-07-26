package meru.andriod.mcom.adapter;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import meru.andriod.mcom.task.AsyncTaskCompletionListener;
import meru.andriod.mcom.task.HtttpAsyncGetTask;

public class EntityListAdapter extends ArrayAdapter<JSONObject>
        implements AsyncTaskCompletionListener {
    private final Context context;
    private final List<JSONObject> listItems;
    private int listViewId;
    private EntityListRowPopulator rowPopulator;

    public EntityListAdapter(Context context,
                             int listViewId,
                             EntityListRowPopulator rowPopulator,
                             List<JSONObject> listItems) {
        super(context,
              listViewId,
              listItems);
        this.context = context;
        this.listViewId = listViewId;
        this.rowPopulator = rowPopulator;
        this.listItems = listItems;
    }

    @Override
    public void onExecutionComplete(JSONObject jsonObject) {
        JSONArray jsonArray = (JSONArray) jsonObject.get("items");

        clear();
        int length = jsonArray.size();

        if (length == 0) {
            rowPopulator.empty();
        }
        else {
            for (int i = 0; i < length; i++) {
                JSONObject entity = (JSONObject) jsonArray.get(i);
                listItems.add(entity);
            }

        }
        this.notifyDataSetChanged();
        this.notifyDataSetInvalidated();
        
    }
    
    public void removeItem(int position) {
        listItems.remove(position);
        this.notifyDataSetChanged();
        this.notifyDataSetInvalidated();
    }

    public static EntityListAdapter getEntityListAdapter(Context context,
                                                         int listViewId,
                                                         EntityListRowPopulator rowPopulator,
                                                         String url) {

        List<JSONObject> listItems = new ArrayList<JSONObject>();
        EntityListAdapter listAdapter = new EntityListAdapter(context,
                                                              listViewId,
                                                              rowPopulator,
                                                              listItems);

        HtttpAsyncGetTask asyncTask = new HtttpAsyncGetTask(listAdapter);
        asyncTask.execute(url);

        return listAdapter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        JSONObject currentItem = listItems.get(position);

        View rowView = inflater.inflate(listViewId,
                                        parent,
                                        false);

        // String url = getImageEndpoint() +
        // currentItem.getImagePath();

        // ImageView imageView = (ImageView)
        // rowView.findViewById(R.id.il_ImageView1);
        // ImageLoader.getInstance().displayImage(url, imageView) ;

        rowPopulator.populate(rowView,
                              currentItem,
                              position);

        return rowView;
    }

}
