package com.beccavin.simplestockmanager.activities;

/**
 * Created by Cyril on 02/01/2015.
 */
public class NavigationMenuUtil {
    public static Class getClass(int index) {
        switch (index) {
            case 0:
                return MouvementActivity.class;
            case 1:
                return CatalogueActivity.class;
            default:
                return null;
        }
    }
}
