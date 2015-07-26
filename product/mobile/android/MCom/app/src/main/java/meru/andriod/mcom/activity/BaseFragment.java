package meru.andriod.mcom.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

public class BaseFragment extends Fragment {
    
    public void attachFragment(int containerView, Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(containerView, fragment).commit();
    }
    
    
    protected String getVariableValue(String variable) {
        String value = null;
        Bundle args = getArguments();
        if (args != null) {
            value = args.getString(variable);
        }

        return value;

    }

    protected static void setVariableValue(Fragment fragment,
                                           String variable,
                                           String value) {
        Bundle args = new Bundle();
        args.putString(variable,
                       value);
        fragment.setArguments(args);
    }
}
