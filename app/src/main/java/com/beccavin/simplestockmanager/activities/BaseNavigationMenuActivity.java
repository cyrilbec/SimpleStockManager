package com.beccavin.simplestockmanager.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.beccavin.simplestockmanager.R;

import java.util.Arrays;

/**
 * Created by Cyril on 02/01/2015.
 */
public abstract class BaseNavigationMenuActivity extends ActionBarActivity {

    private ListView navigationMenu;
    private DrawerLayout navigationMenuLayout;
    private ActionBarDrawerToggle navigationMenuToggle;
    private NavigationMenuListAdapter menuListAdapter;
    private FrameLayout activityContent;
    private String[] menuItems;

    private Toolbar toolbar;
    private View toolbarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // load activity layout
        super.setContentView(R.layout.activity_navigationmenu);

        activityContent = (FrameLayout)findViewById(R.id.activityContent);
        navigationMenuLayout = (DrawerLayout)findViewById(R.id.navigationMenuLayout);
        navigationMenu = (ListView)findViewById(R.id.navigationMenu);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        // create navigation drawer
        createNavigationDrawer();

        // create action bar
        createActionBar();
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = getLayoutInflater().inflate(layoutResID, null);
        activityContent.addView(view);
    }

    private String[] getMenuItems() {
        Resources res = getResources();
        String[] items = res.getStringArray(R.array.navigation_menu_items);
        return items;
    }

    private void createNavigationDrawer(){
        // menu initialization
        menuItems = getMenuItems();
        menuListAdapter = new NavigationMenuListAdapter(this, R.layout.navigation_menu_item, R.id.navigationMenuNoItem, Arrays.asList(menuItems));
        navigationMenu.setAdapter(menuListAdapter);

        // click on a menu item
        navigationMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class targetActivityClass = NavigationMenuUtil.getClass(position);

                if (targetActivityClass != null) {
                    Intent startActivityIntent = new Intent(BaseNavigationMenuActivity.this, targetActivityClass);
                    BaseNavigationMenuActivity.this.startActivity(startActivityIntent);
                } else {
                    Toast.makeText(BaseNavigationMenuActivity.this, "not implemented :(", Toast.LENGTH_SHORT).show();
                }

                // close the navigation menu
                navigationMenuLayout.closeDrawers();
            }
        });

        // init navigation toogle
        navigationMenuToggle = new ActionBarDrawerToggle(this, navigationMenuLayout, R.string.menu_open, R.string.menu_open){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // show menu title in action bar
                //getSupportActionBar().setTitle(R.string.menu_open);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // show activity title in action bar
                //getSupportActionBar().setTitle(BaseNavigationMenuActivity.this.getTitle());
            }
        };
        navigationMenuLayout.setDrawerListener(navigationMenuToggle);
    }

    protected void createActionBar(){
        // inflate view
        toolbarView = getLayoutInflater().inflate(R.layout.actionbar, null);
        toolbar.addView(toolbarView);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // open navigation drawer button
        ImageButton buttonShowMenu = (ImageButton)toolbarView.findViewById(R.id.actionBarShowMenu);
        buttonShowMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseNavigationMenuActivity.this.navigationMenuLayout.openDrawer(Gravity.LEFT);
            }
        });

        // title
        TextView title = (TextView)toolbarView.findViewById(R.id.actionBarTitle);
        title.setText(getTitle());
    }

    protected Toolbar getToolbar(){
        return toolbar;
    }
}
