package com.beccavin.simplestockmanager.activities;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.beccavin.simplestockmanager.R;

public class MouvementActivity extends ActionBarActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouvement);

        toolbar = (Toolbar)findViewById(R.id.mouvementToolbar);

        setSupportActionBar(toolbar);

        createToolbar();
    }


    private void createToolbar() {
        toolbar.inflateMenu(R.menu.menu_mouvement);
        toolbar.setTitle(getTitle());
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();

                switch(id) {
                    case R.id.action_mouvement_cancel: {
                        // back to parent activity
                        finish();
                        return true;
                    }
                    case R.id.action_mouvement_valid: {
                        // TODO : save datas to database

                        // back to parent activity
                        Toast.makeText(MouvementActivity.this, "Mouvement créé", Toast.LENGTH_SHORT).show();
                        finish();
                        return true;
                    }
                }

                //return super.onOptionsItemSelected(item);
                return false;
            }
        });
    }
}
