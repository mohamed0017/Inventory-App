package com.myapp.android.inventoryapp.dpHelper;

import android.provider.BaseColumns;

/**
 * Created by user on 8/5/2017.
 */

public class InventoryContract {

    public InventoryContract() {

    }

    public static final class StockEntry implements BaseColumns {

        public static final String TABLE_NAME = "stock";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_SUPPLIER_PHONE = "supplier_phone";
        public static final String COLUMN_SUPPLIER_EMAIL = "supplier_email";
        public static final String COLUMN_IMAGE = "image";

        public static final String CREATE_TABLE_STOCK = "CREATE TABLE " +
                InventoryContract.StockEntry.TABLE_NAME + "(" +
                InventoryContract.StockEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                InventoryContract.StockEntry.COLUMN_NAME + " TEXT NOT NULL," +
                InventoryContract.StockEntry.COLUMN_PRICE + " TEXT NOT NULL," +
                InventoryContract.StockEntry.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0," +
                InventoryContract.StockEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL," +
                InventoryContract.StockEntry.COLUMN_SUPPLIER_PHONE + " TEXT NOT NULL," +
                InventoryContract.StockEntry.COLUMN_SUPPLIER_EMAIL + " TEXT NOT NULL," +
                StockEntry.COLUMN_IMAGE + " TEXT NOT NULL" + ");";
    }
}
