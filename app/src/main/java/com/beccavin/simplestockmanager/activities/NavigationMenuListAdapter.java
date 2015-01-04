package com.beccavin.simplestockmanager.activities;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beccavin.simplestockmanager.R;

import java.util.List;

/**
 * Created by Cyril on 02/01/2015.
 */
public class NavigationMenuListAdapter extends ArrayAdapter<String> {

    private List<String> items;

    public NavigationMenuListAdapter(Context context, int resource, int textViewResourceId, List<String> objects) {
        super(context, resource, textViewResourceId, objects);
        items = objects;
    }

    private static class ItemViewHolder
    {
        public ImageView navigationMenuItemIcon;
        public TextView navigationMenuItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewHolder viewHolder;
        Activity activity = (Activity)getContext();

        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.navigation_menu_item, null);

            // create viewHolder and set views
            viewHolder = new ItemViewHolder();
            viewHolder.navigationMenuItemIcon = (ImageView)convertView.findViewById(R.id.navigationMenuItemIcon);
            viewHolder.navigationMenuItem = (TextView)convertView.findViewById(R.id.navigationMenuItemText);

            convertView.setTag(viewHolder);
        }

        viewHolder = (ItemViewHolder)convertView.getTag();
        String val = getItem(position);
        //  fill views with values
        viewHolder.navigationMenuItem.setText(val);
        //viewHolder.navigationMenuItemIcon.setImageDrawable();

        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        // test if current activity is that item
        Class currentActivityClass = NavigationMenuUtil.getClass(position);
        return currentActivityClass != getContext().getClass();
    }
}
