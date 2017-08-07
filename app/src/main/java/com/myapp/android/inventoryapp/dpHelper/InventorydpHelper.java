package com.myapp.android.inventoryapp.dpHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 8/4/2017.
 */

public class InventorydpHelper extends SQLiteOpenHelper {

    public final static String DB_NAME = "inventory.db";
    public final static int DB_VERSION = 1;
    public final static String LOG_TAG = InventorydpHelper.class.getCanonicalName();

    public InventorydpHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(InventoryContract.StockEntry.CREATE_TABLE_STOCK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertItem(com.myapp.android.inventoryapp.model.Inventory item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InventoryContract.StockEntry.COLUMN_NAME, item.getProductName());
        values.put(InventoryContract.StockEntry.COLUMN_PRICE, item.getPrice());
        values.put(InventoryContract.StockEntry.COLUMN_QUANTITY, item.getQuantity());
        values.put(InventoryContract.StockEntry.COLUMN_SUPPLIER_NAME, item.getSupplierName());
        values.put(InventoryContract.StockEntry.COLUMN_SUPPLIER_PHONE, item.getSupplierPhone());
        values.put(InventoryContract.StockEntry.COLUMN_SUPPLIER_EMAIL, item.getSupplierEmail());
        values.put(InventoryContract.StockEntry.COLUMN_IMAGE, item.getImage());
        long id = db.insert(InventoryContract.StockEntry.TABLE_NAME, null, values);
    }

    public Cursor readStock() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                InventoryContract.StockEntry._ID,
                InventoryContract.StockEntry.COLUMN_NAME,
                InventoryContract.StockEntry.COLUMN_PRICE,
                InventoryContract.StockEntry.COLUMN_QUANTITY,
                InventoryContract.StockEntry.COLUMN_SUPPLIER_NAME,
                InventoryContract.StockEntry.COLUMN_SUPPLIER_PHONE,
                InventoryContract.StockEntry.COLUMN_SUPPLIER_EMAIL,
                InventoryContract.StockEntry.COLUMN_IMAGE
        };
        Cursor cursor = db.query(
                InventoryContract.StockEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    public Cursor readItem(long itemId) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                InventoryContract.StockEntry._ID,
                InventoryContract.StockEntry.COLUMN_NAME,
                InventoryContract.StockEntry.COLUMN_PRICE,
                InventoryContract.StockEntry.COLUMN_QUANTITY,
                InventoryContract.StockEntry.COLUMN_SUPPLIER_NAME,
                InventoryContract.StockEntry.COLUMN_SUPPLIER_PHONE,
                InventoryContract.StockEntry.COLUMN_SUPPLIER_EMAIL,
                InventoryContract.StockEntry.COLUMN_IMAGE
        };
        String selection = InventoryContract.StockEntry._ID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(itemId)};

        Cursor cursor = db.query(
                InventoryContract.StockEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        return cursor;
    }

    public void updateItem(long currentItemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InventoryContract.StockEntry.COLUMN_QUANTITY, quantity);
        String selection = InventoryContract.StockEntry._ID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(currentItemId)};
        db.update(InventoryContract.StockEntry.TABLE_NAME,
                values, selection, selectionArgs);
    }

    public void sellOneItem(long itemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        int newQuantity = 0;
        if (quantity > 0) {
            newQuantity = quantity - 1;
        }
        ContentValues values = new ContentValues();
        values.put(InventoryContract.StockEntry.COLUMN_QUANTITY, newQuantity);
        String selection = InventoryContract.StockEntry._ID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(itemId)};
        db.update(InventoryContract.StockEntry.TABLE_NAME,
                values, selection, selectionArgs);
    }

}
