package meru.andriod.mcom.activity;

import org.json.simple.JSONObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import meru.andriod.mcom.R;
import meru.andriod.mcom.adapter.BaseEntityListRowPopulator;
import meru.andriod.mcom.adapter.EntityListAdapter;
import meru.andriod.mcom.adapter.EntityListRowPopulator;
import meru.andriod.mcom.listener.CheckoutCartListener;
import meru.andriod.mcom.listener.RemoveCartItemListener;

public class CartFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_cart,
                                         container,
                                         false);
        loadProducts(rootView,
                       R.id.cartProductList);
        return rootView;
    }


    
    protected void loadProducts(View view, int listId) {
        final ListView listView = (ListView) view.findViewById(listId);
        
        final View activityView = view.findViewById(R.id.checoutLayout); 
        
        BaseEntityListRowPopulator.setVisibile(activityView, R.id.item_text, true);
        BaseEntityListRowPopulator.setVisibile(activityView, R.id.item_count, true);
        BaseEntityListRowPopulator.setVisibile(activityView, R.id.shipping_text, true);
        BaseEntityListRowPopulator.setVisibile(activityView, R.id.shipping_amount, true);
        BaseEntityListRowPopulator.setVisibile(activityView, R.id.total_amount, true);
        BaseEntityListRowPopulator.setVisibile(activityView, R.id.checkout, true);
        BaseEntityListRowPopulator.setVisibile(activityView, R.id.cartProductList, true);
        BaseEntityListRowPopulator.setVisibile(activityView, R.id.cart_empty, false);

        Button checkoutButton = (Button) activityView.findViewById(R.id.checkout);
        checkoutButton.setOnClickListener(new CheckoutCartListener(this));
        
        
        EntityListRowPopulator rowPopulator = new BaseEntityListRowPopulator() {

            @Override
            public void populate(View listRow, JSONObject shoppingCartLineItem, int position) {
                String shoppingCartLineItemId = (String)shoppingCartLineItem.get("id");
                JSONObject productItem = (JSONObject)shoppingCartLineItem.get("productLineItem");
                JSONObject product = (JSONObject)productItem.get("product");
                JSONObject unitOfMessure = (JSONObject)productItem.get("unitOfMeasure");
                String name = product.get("name")+" "+productItem.get("quantity")+" "+unitOfMessure.get("value");

                setTextValue(listRow, R.id.cartListView_Name, name);
                setTextValue(listRow, R.id.cartListView_MRP, (String) productItem.get("mrp"));
                
//                Spinner spinner = (Spinner) listRow.findViewById(R.id.spinner1);
//                ImageButton imageButtom = (ImageButton) listRow.findViewById(R.id.delete);
//                textView = (TextView) listRow.findViewById(R.id.product_value);
//                textView = (TextView) listRow.findViewById(R.id.qty_text);
//                textView = (TextView) listRow.findViewById(R.id.product_bb);
//                textView = (TextView) listRow.findViewById(R.id.product_bbvalue);
//                textView = (TextView) listRow.findViewById(R.id.product_savings);
//                textView = (TextView) listRow.findViewById(R.id.product_savingsvalue);
                
                
                ImageButton imageButton = (ImageButton) listRow.findViewById(R.id.cartListView_Delete);
                imageButton.setOnClickListener(new RemoveCartItemListener(listRow, position, shoppingCartLineItemId));
            }

            @Override
            public void empty() {
                System.out.println(">>>>>>>>>>>>>>> EMPTY ");
                setVisibile(activityView, R.id.item_text, false);
                setVisibile(activityView, R.id.item_count, false);
                setVisibile(activityView, R.id.shipping_text, false);
                setVisibile(activityView, R.id.shipping_amount, false);
                setVisibile(activityView, R.id.total_amount, false);
                setVisibile(activityView, R.id.checkout, false);
                setVisibile(activityView, R.id.cartProductList, false);
                setVisibile(activityView, R.id.cart_empty, true);
                
            }
        };
        


        EntityListAdapter listAdapter = EntityListAdapter.getEntityListAdapter(this.getActivity(),
                                                                               R.layout.fragment_cart_list_item,
                                                                               rowPopulator,
                                                                               "ShoppingCartLineItem");

        listView.setAdapter(listAdapter);
        listView.setTextFilterEnabled(true);
    }
}
