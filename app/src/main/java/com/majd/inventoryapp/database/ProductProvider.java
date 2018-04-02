package com.majd.inventoryapp.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by majd on 3/23/18.
 */

public class ProductProvider extends ContentProvider {
    //declare an integers ti define the uri's for the queries that we want to made
    private static final int Product = 100;
    private static final int Product_ID = 101;

    //declare the database that the content provider want to access
    private ProductDBHelper mProductDbHelper;
    //declare the uriMatcher
    private final static UriMatcher sUriMatcher = buildUriMatcher();

    @Override
    public boolean onCreate() {
        mProductDbHelper = new ProductDBHelper(getContext());
        return true;
    }

    //build the uri matcher to match each uri to ies integers
    public static UriMatcher buildUriMatcher() {
        String content = ProductContract.CONTENT_AUTHORITY;
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(content, ProductContract.PATH_PRODUCT, Product);
        matcher.addURI(content, ProductContract.PATH_PRODUCT + "/#", Product_ID);
        return matcher;

    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = mProductDbHelper.getReadableDatabase();
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case Product:
                retCursor = db.query(ProductContract.ProductEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case Product_ID:
                long _id = ContentUris.parseId(uri);
                retCursor = db.query(ProductContract.ProductEntry.TABLE_NAME,
                        projection,
                        ProductContract.ProductEntry._ID + "=?",
                        new String[]{String.valueOf(_id)},
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase db = mProductDbHelper.getWritableDatabase();
        Uri returnUri;

        switch (sUriMatcher.match(uri)) {
            case Product: {
                long _id = db.insert(ProductContract.ProductEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    returnUri = ProductContract.ProductEntry.buildProductUri(_id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mProductDbHelper.getWritableDatabase();
        int rowsDeleted;
        // this makes delete all rows return the number of rows deleted
        if (null == selection) selection = "1";
        switch (sUriMatcher.match(uri)) {
            case Product:
                rowsDeleted = db.delete(
                        ProductContract.ProductEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        // Because a null deletes all rows
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
