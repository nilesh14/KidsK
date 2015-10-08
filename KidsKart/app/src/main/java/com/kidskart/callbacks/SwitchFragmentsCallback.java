package com.kidskart.callbacks;


import android.support.v4.app.Fragment;

import com.kidskart.data.category.Filters;

/**
 * Created by Nilesh on 28/05/15.
 */
public interface SwitchFragmentsCallback {
    public void switchFragment(Fragment fragment, int fragID, Object extras);
    public void switchFragmentAddToBackStack(Fragment fragment, int fragID, Object extras, boolean addToBackStack);
    //public void switchFilterFragment(Fragment fragment, Filters filters);
    public void popBackStack(boolean popBackStack);
}
