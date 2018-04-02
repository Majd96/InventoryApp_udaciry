package com.majd.inventoryapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.majd.inventoryapp.database.ProductContract;
import com.majd.inventoryapp.database.ProductDBHelper;

public class MainActivity extends AppCompatActivity {
    private ProductDBHelper mDbHelper;
    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = new ProductDBHelper(this);
        //insert one row of product
        insertProduct("nutella", 200, "100", "nutella company", "supplier@nutella.com", "0599254158");
        //query one product
        Product product = queryProductByName("nutella");
        //log the queried data
        Log.d(TAG, "Product name : " + product.getName()
                + "\n Product quantity : " + product.getQuantity()
                + "\n Product price : " + product.getPrice()
                + "\n Product supplier name : " + product.getSuppiler()
                + "\n Product supplier email : " + product.getSuppilerEmail()
                + "\n Product supplier phone number : " + product.getSuppilerPhoneNo());

    }

    private void insertProduct(String name, int quantity,
                               String price, String supplier, String supplierEmail, String supplierPhone) {

        ContentValues values = new ContentValues();

        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME, name);
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, quantity);
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE, price);
        values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME, supplier);
        values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL, supplierEmail);
        values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER, supplierPhone);

        getContentResolver().insert(ProductContract.ProductEntry.CONTENT_URI, values);

    }


    private Product queryProductByName(String name) {

        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        Product product = null;

        Cursor cursor = database.query(
                ProductContract.ProductEntry.TABLE_NAME
                , null
                , ProductContract.ProductEntry.COLUMN_PRODUCT_NAME + " =?"
                , new String[]{name}
                , null
                , null
                , null
        );


        if (cursor.moveToFirst()) {

            int nameColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME);
            int quantityColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANTITY);
            int priceColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE);
            int supplierNameColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME);
            int supplierEmailColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL);
            int supplierNumberColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER);

            String productName = cursor.getString(nameColumnIndex);
            Integer quantity = cursor.getInt(quantityColumnIndex);
            String price = cursor.getString(priceColumnIndex);
            String supplierName = cursor.getString(supplierNameColumnIndex);
            String supplierEmail = cursor.getString(supplierEmailColumnIndex);
            String supplierNumber = cursor.getString(supplierNumberColumnIndex);

            product = new Product(productName, quantity, price, supplierName, supplierEmail, supplierNumber);


        }
        cursor.close();

        return product;
    }
}
