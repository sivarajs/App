package meru.andriod.mcom.activity;

import meru.andriod.mcom.R;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate() {
        setContentView(R.layout.activity_main);
        //loadCategories(R.id.mainCategoryListView);
        
        /*final ListView listView = (ListView)findViewById(R.id.mainCategoryListView);
        
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,
                    long id) {
                
                String selectedCategory = ((TextView)view).getText().toString();
                Intent intent = new Intent(view.getContext(), ProductCategoryActivity.class);
                intent.putExtra(App.SELECTED_PRODUCT_CATEGORY, "0");
                startActivity(intent);
                
            }
        });*/
        
    }

}
