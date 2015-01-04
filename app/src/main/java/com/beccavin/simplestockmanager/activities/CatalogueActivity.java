package com.beccavin.simplestockmanager.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.beccavin.simplestockmanager.R;

public class CatalogueActivity extends BaseNavigationMenuActivity {

    private ListView catalogueList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_catalogue);

        catalogueList = (ListView)findViewById(R.id.catalogueList);

    }

    @Override
    protected void createActionBar() {
        super.createActionBar();

        Toolbar toolbar = super.getToolbar();

        toolbar.inflateMenu(R.menu.menu_catalogue);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = menuItem.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                    return true;
                }

                return false;
            }
        });
    }
}
