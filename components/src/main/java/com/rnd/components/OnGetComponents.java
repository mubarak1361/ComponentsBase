package com.rnd.components;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

/**
 * Created by CIPL0224 on 1/31/2017.
 */

public interface OnGetComponents {
    public Toolbar getToolbar();
    public void loadFragment(Fragment fragment);
}
