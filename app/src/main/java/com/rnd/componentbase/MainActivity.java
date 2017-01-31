package com.rnd.componentbase;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.rnd.components.DrawerActivity;

public class MainActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOpenCloseDrawerContentDescRes(R.string.app_name,R.string.app_name);
    }

    @Override
    protected boolean onNavigationViewItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_first_fragment:
                break;
            case R.id.nav_second_fragment:
                break;
            case R.id.nav_third_fragment:
                break;
        }
        return true;
    }

    @Override
    protected int inflateNavigationViewHeader() {
        return R.layout.nav_header;
    }

    @Override
    protected int inflateNavigationViewMenu() {
        return R.menu.drawer_item;
    }
}
