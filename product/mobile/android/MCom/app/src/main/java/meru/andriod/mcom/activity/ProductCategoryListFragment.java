package meru.andriod.mcom.activity;

import org.json.simple.JSONObject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import meru.andriod.mcom.App;
import meru.andriod.mcom.R;
import meru.andriod.mcom.adapter.BaseEntityListRowPopulator;
import meru.andriod.mcom.adapter.EntityListAdapter;
import meru.andriod.mcom.adapter.EntityListRowPopulator;

public class ProductCategoryListFragment extends BaseFragment {

    public ProductCategoryListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_product_category_list,
                                         container,
                                         false);
        loadCategories(rootView,
                       R.id.productCategoryListView);
        return rootView;
    }

    protected void loadCategories(View view, int listId) {
        final ListView listView = (ListView) view.findViewById(listId);

        String id = getVariableValue(App.SELECTED_PRODUCT_CATEGORY);

        EntityListRowPopulator rowPopulator = new BaseEntityListRowPopulator() {

            @Override
            public void populate(View listRow,
                                 JSONObject productCategory,
                                 int position) {
                setTextValue(listRow,
                             R.id.productCategloryListNameText,
                             (String) productCategory.get("name"));
            }

        };

        EntityListAdapter listAdapter = EntityListAdapter.getEntityListAdapter(this.getActivity(),
                                                                               R.layout.fragment_product_category_list_item,
                                                                               rowPopulator,
                                                                               "ProductCategory?parentId="
                                                                                       + id
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

                Fragment fragment = null;
                if (qName.split("[/]").length < 4) {
                    fragment = new ProductCategoryListFragment();
                }
                else {

                    fragment = new ProductListFragment();
                }

                setVariableValue(fragment,
                                 App.SELECTED_PRODUCT_CATEGORY,
                                 selectedCategory);
                attachFragment(R.id.frame_container,
                               fragment);

            }

        });
    }
}
