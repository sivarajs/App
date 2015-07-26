package meru.andriod.mcom.activity;

import org.json.simple.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import meru.andriod.mcom.App;
import meru.andriod.mcom.R;
import meru.andriod.mcom.adapter.BaseEntityListRowPopulator;
import meru.andriod.mcom.adapter.EntityListAdapter;
import meru.andriod.mcom.adapter.EntityListRowPopulator;
import meru.andriod.mcom.listener.AddCartItemListener;

public class ProductListFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_product_list,
                                         container,
                                         false);
        loadProducts(rootView,
                       R.id.productListView);
        return rootView;
    }

    
    protected void loadProducts(View view, int listId) {
        final ListView listView = (ListView) view.findViewById(listId);
        String productCategoryId = getVariableValue(App.SELECTED_PRODUCT_CATEGORY);
        
        EntityListRowPopulator rowPopulator = new BaseEntityListRowPopulator() {

            @Override
            public void populate(View listRow, JSONObject productItem, int position) {
                
                String name = App.getProductNameWithQuantity(productItem);

                setTextValue(listRow, R.id.productListView_Name, name);
                setTextValue(listRow, R.id.productListView_MRP, (String) productItem.get("mrp"));
                
                EditText qtyField = (EditText) listRow.findViewById(R.id.productListView_Qty);
                
                ImageButton imageButton = (ImageButton) listRow.findViewById(R.id.productListView_addToCart);
                imageButton.setOnClickListener(new AddCartItemListener(listRow.getContext(), productItem, qtyField));
                
//                Spinner spinner = (Spinner) listRow.findViewById(R.id.spinner1);
//                ImageButton imageButtom = (ImageButton) listRow.findViewById(R.id.delete);
//                textView = (TextView) listRow.findViewById(R.id.product_value);
//                textView = (TextView) listRow.findViewById(R.id.qty_text);
//                textView = (TextView) listRow.findViewById(R.id.product_bb);
//                textView = (TextView) listRow.findViewById(R.id.product_bbvalue);
//                textView = (TextView) listRow.findViewById(R.id.product_savings);
//                textView = (TextView) listRow.findViewById(R.id.product_savingsvalue);
            }

        };
        


        EntityListAdapter listAdapter = EntityListAdapter.getEntityListAdapter(this.getActivity(),
                                                                               R.layout.fragment_product_list_item,
                                                                               rowPopulator,
                                                                               "ProductLineItem?product.productCategory.id="
                                                                                       + productCategoryId
                                                                                       + "&orderBy=sortOrder");

        listView.setAdapter(listAdapter);
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view,
                                    int position,
                                    long id) {

                ListView parentList = (ListView) view.getParent();

                ListAdapter adapter = parentList.getAdapter();

                JSONObject entity = (JSONObject) adapter.getItem(position);
                String selectedCategory = (String) entity.get("id");

                String qName = (String) entity.get("qualifiedName");

                if (qName.split("[/]").length < 4) {

                   /* Intent intent = new Intent(view.getContext(),
                            ProductCategoryListActivity.class);
                    intent.putExtra(App.SELECTED_PRODUCT_CATEGORY,
                            selectedCategory);
                    startActivity(intent);*/
                } else {

                }
            }

        });
    }
    

}
