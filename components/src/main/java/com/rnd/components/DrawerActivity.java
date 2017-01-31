package com.rnd.components;

import android.content.res.Configuration;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public abstract class DrawerActivity extends AppCompatActivity implements OnNavigationItemSelectedListener, OnGetComponents {

    @LayoutRes
    protected abstract int inflateNavigationViewHeader();

    @MenuRes
    protected abstract int inflateNavigationViewMenu();

    protected abstract boolean onNavigationViewItemSelected(@NonNull MenuItem item);

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private FragmentManager fragmentManager;
    private int openDrawerConentDescRes;
    private int closeDrawerContentDescRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nvView);

        if (openDrawerConentDescRes != 0 && closeDrawerContentDescRes != 0) {
            drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, openDrawerConentDescRes, closeDrawerContentDescRes);
        } else {
            drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        }

        drawerLayout.addDrawerListener(drawerToggle);

        navigationView.inflateHeaderView(inflateNavigationViewHeader());
        navigationView.inflateMenu(inflateNavigationViewMenu());
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();

    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        setTitle(item.getTitle());
        drawerLayout.closeDrawers();
        return onNavigationViewItemSelected(item);
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void loadFragment(Fragment fragment) {
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
    }

    public void setOpenCloseDrawerContentDescRes(@StringRes int openDrawerConentDescRes, @StringRes int closeDrawerContentDescRes) {
        this.openDrawerConentDescRes = openDrawerConentDescRes;
        this.closeDrawerContentDescRes = closeDrawerContentDescRes;
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
