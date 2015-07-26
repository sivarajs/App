package meru.andriod.mcom.listener;

import org.json.simple.JSONObject;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;
import meru.andriod.mcom.adapter.EntityListAdapter;
import meru.andriod.mcom.task.AsyncTaskCompletionListener;
import meru.andriod.mcom.task.HtttpAsyncDeleteTask;

public class RemoveCartItemListener implements  OnClickListener, AsyncTaskCompletionListener {

    private View listRow;
    private int position;
    private String shoppingCartLineItemId;
    
    
    public RemoveCartItemListener(View listRow, int position, String shoppingCartLineItemId) {
        this.listRow = listRow;
        this.position = position;
        this.shoppingCartLineItemId = shoppingCartLineItemId;
    }
    
    @Override
    public void onClick(View button) {
        HtttpAsyncDeleteTask asyncTask = new HtttpAsyncDeleteTask(this);
        asyncTask.execute("ShoppingCartLineItem/"+shoppingCartLineItemId);
        
    }

    @Override
    public void onExecutionComplete(JSONObject jsonObject) {
        ListView listView = (ListView)listRow.getParent();
        EntityListAdapter listAdapter = (EntityListAdapter)listView.getAdapter();
        listAdapter.removeItem(position);
        
        Toast.makeText(listRow.getContext(),
                       "The uiem has been removed from Cart",
                       Toast.LENGTH_LONG)
                .show();
        

    }
    
}
