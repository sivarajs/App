package meru.andriod.mcom.listener;

import org.json.simple.JSONObject;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import meru.andriod.mcom.ShoppingCart;
import meru.andriod.mcom.activity.BaseFragment;
import meru.andriod.mcom.task.AsyncTaskCompletionListener;
import meru.andriod.mcom.util.AndriodUI;

public class CheckoutCartListener implements  OnClickListener, AsyncTaskCompletionListener {

    private BaseFragment context;
    
    
    public CheckoutCartListener(BaseFragment context) {
        this.context = context;
    }
    
    @Override
    public void onClick(View button) {
        new ShoppingCart().checkout(context);
        AndriodUI.showToast(context.getActivity(), "The order has been successfully created");
//        HtttpAsyncGetTask asyncTask = new HtttpAsyncGetTask(this);
//        asyncTask.execute("ShoppingCart/"+shoppingCartLineItemId);
        
    }

    @Override
    public void onExecutionComplete(JSONObject jsonObject) {

        
        Toast.makeText(context.getActivity(),
                       "The item has been removed from Cart",
                       Toast.LENGTH_LONG)
                .show();
        

    }
    
}
