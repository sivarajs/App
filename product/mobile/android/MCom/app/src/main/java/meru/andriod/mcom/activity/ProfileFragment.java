package meru.andriod.mcom.activity;

import org.json.simple.JSONObject;

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

public class ProfileFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile,
                                         container,
                                         false);
       
        return rootView;
    }

    

}
