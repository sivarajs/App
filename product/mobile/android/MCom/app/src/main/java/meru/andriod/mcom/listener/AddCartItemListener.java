package meru.andriod.mcom.listener;

import org.json.simple.JSONObject;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import meru.andriod.mcom.App;
import meru.andriod.mcom.task.AsyncTaskCompletionListener;
import meru.andriod.mcom.task.HtttpAsyncPostTask;

public class AddCartItemListener implements  OnClickListener, AsyncTaskCompletionListener {

    private Context context;
    private JSONObject productItem;
    private EditText qtyField;
    
    
    public AddCartItemListener(Context context, JSONObject productItem, EditText qtyField) {
        this.context = context;
        this.productItem = productItem;
        this.qtyField = qtyField;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void onClick(View button) {
        

        
        JSONObject item = new JSONObject();
        JSONObject productLineItem = new JSONObject();
        
        productLineItem.put("id", productItem.get("id"));
        
        String qty = qtyField.getText().toString();
        if (qty.trim().length() == 0) {
            Toast.makeText(context,
                           "Please specify quantity",
                           Toast.LENGTH_LONG)
                    .show();
            return;
        }
        
        item.put("productLineItem", productLineItem);
        item.put("quantity", qty);

        
        HtttpAsyncPostTask asyncTask = new HtttpAsyncPostTask(this, item);
        asyncTask.execute("ShoppingCartLineItem");
        
    }

    @Override
    public void onExecutionComplete(JSONObject jsonObject) {

        
        System.out.println("###### "+jsonObject);
        String name = App.getProductNameWithQuantity(productItem);
        Toast.makeText(context,
                       "Item " + name
                               + " added to Cart",
                       Toast.LENGTH_LONG)
                .show();
        

    }
    
}
