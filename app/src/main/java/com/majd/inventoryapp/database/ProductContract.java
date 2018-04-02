package com.majd.inventoryapp.database;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by majd on 3/23/18.
 */

public class ProductContract {
    //to know which content provider to access
    public static final String CONTENT_AUTHORITY = "com.majd.inventoryapp";

    public static final Uri BASE_CONENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    //paths for the data that we want the content provider to fetch from the database
    public static final String PATH_PRODUCT = "products";

    //create an inner class that implements the basecolumns for each table in the data base
    public static final class ProductEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONENT_URI.buildUpon().appendPath(PATH_PRODUCT).build();

        public final static String TABLE_NAME = "products";
        public final static String COLUMN_PRODUCT_NAME = "productName";
        public final static String COLUMN_PRODUCT_PRICE = "price";
        public final static String COLUMN_PRODUCT_QUANTITY = "quantity";
        public final static String COLUMN_SUPPLIER_NAME = "supplierName";
        public final static String COLUMN_SUPPLIER_EMAIL = "supplierEmail";
        public final static String COLUMN_SUPPLIER_PHONE_NUMBER = "supplier_phoneNumber";

        public static Uri buildProductUri(long id) {

            return ContentUris.withAppendedId(CONTENT_URI, id);

        }


    }
}
