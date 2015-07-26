package meru.andriod.mcom.adapter;

import android.view.View;
import android.widget.TextView;

public abstract class BaseEntityListRowPopulator implements EntityListRowPopulator {

    @Override
    public void empty() {
        
    }
    
    public static TextView setTextValue(View parentView, int childViewId, String value) {
        TextView textView = (TextView) parentView.findViewById(childViewId);
        textView.setText(value);
        return textView;
    }
    
    public static View setVisibile(View parentView, int childViewId, boolean visible) {
        View view = parentView.findViewById(childViewId);
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        return view;
    }
   
}
