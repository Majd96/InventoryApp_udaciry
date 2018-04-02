package com.majd.inventoryapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by majd on 3/23/18.
 */

public class ProductDBHelper extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "productList.db";

    public ProductDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        sqLiteDatabase.execSQL(

                "CREATE TABLE " + ProductContract.ProductEntry.TABLE_NAME + " (" +
                        ProductContract.ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ProductContract.ProductEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, " +
                        ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY + " INTEGER NOT NULL, " +
                        ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE + " TEXT NOT NULL, " +
                        ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, " +
                        ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL + " TEXT," +
                        ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER + " TEXT NOT NULL);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //this is called when ever the dataBase version incremented ,when the schema need changes
        //or when a new table need to created

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ProductContract.ProductEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
        DATABASE_VERSION++;

    }
}
