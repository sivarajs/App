package meru.andriod.mcom;

import meru.andriod.mcom.activity.BaseFragment;
import meru.andriod.mcom.activity.LoginFragment;

public class ShoppingCart {

    
    public void checkout(BaseFragment parentFragment) {
        
        parentFragment.attachFragment(R.id.frame_container,
                       new LoginFragment());
        
  /*    Intent intent = new Intent(parentActivity.getBaseContext(),
                            LoginActivity.class);
    
//    intent.putExtra(App.SELECTED_PRODUCT_CATEGORY,
//                    selectedCategory);
       parentActivity.startActivity(intent);*/
    }
    
    
}
