package com.kidskart.callbacks;


import android.support.v4.app.Fragment;

/**
 * Created by Nilesh on 28/05/15.
 */
public interface SwitchFragmentsCallback {
    public void switchFragment(Fragment fragment, int fragID, Object extras);
    public void switchFragmentAddToBackStack(Fragment fragment, int fragID, Object extras, boolean addToBackStack);
    public void popBackStack(boolean popBackStack);
}
