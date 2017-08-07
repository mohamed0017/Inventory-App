package com.myapp.android.inventoryapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.myapp.android.inventoryapp.dpHelper.InventorydpHelper;
import com.myapp.android.inventoryapp.model.Inventory;

/**
 * Created by user on 8/3/2017.
 */
public class MainActivity extends AppCompatActivity {

    InventoryAdapter adapter;
    InventorydpHelper dbHelper;
    int lastItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new InventorydpHelper(this);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });

        final ListView listView = (ListView) findViewById(R.id.list_view);

        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);

        Cursor cursor = dbHelper.readStock();
        adapter = new InventoryAdapter(this, cursor);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == 0) return;
                final int currentFirstVisibleItem = view.getFirstVisiblePosition();
                if (currentFirstVisibleItem > lastItem) {
                    fab.show();
                } else if (currentFirstVisibleItem < lastItem) {
                    fab.hide();
                }
                lastItem = currentFirstVisibleItem;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.swapCursor(dbHelper.readStock());
    }

    public void clickOnViewItem(long id) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("itemId", id);
        startActivity(intent);
    }

    public void clickOnSale(long id, int quantity) {
        dbHelper.sellOneItem(id, quantity);
        adapter.swapCursor(dbHelper.readStock());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_dummy_data:
                // add dummy data for testing
                addDummyData();
                adapter.swapCursor(dbHelper.readStock());
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Add data for demo purposes
     */
    private void addDummyData() {
        Inventory gummibears = new Inventory(
                "Gummibears",
                "10 €",
                45,
                "Haribo GmbH",
                "+49 000 000 0000",
                "haribo@sweet.com",
                "android.resource://eu.laramartin.inventorymanager/drawable/gummibear");
        dbHelper.insertItem(gummibears);

        Inventory peaches = new Inventory(
                "Peaches",
                "10 €",
                24,
                "Haribo GmbH",
                "+49 000 000 0000",
                "haribo@sweet.com",
                "android.resource://eu.laramartin.inventorymanager/drawable/peach");
        dbHelper.insertItem(peaches);

        Inventory cherries = new Inventory(
                "Cherries",
                "11 €",
                74,
                "Haribo GmbH",
                "+49 000 000 0000",
                "haribo@sweet.com",
                "android.resource://eu.laramartin.inventorymanager/drawable/cherry");
        dbHelper.insertItem(cherries);

        Inventory cola = new Inventory(
                "Cola",
                "13 €",
                44,
                "Haribo GmbH",
                "+49 000 000 0000",
                "haribo@sweet.com",
                "android.resource://eu.laramartin.inventorymanager/drawable/cola");
        dbHelper.insertItem(cola);

        Inventory fruitSalad = new Inventory(
                "Fruit salad",
                "20 €",
                34,
                "Haribo GmbH",
                "+49 000 000 0000",
                "haribo@sweet.com",
                "android.resource://eu.laramartin.inventorymanager/drawable/fruit_salad");
        dbHelper.insertItem(fruitSalad);

        Inventory smurfs = new Inventory(
                "Smurfs",
                "12 €",
                26,
                "Haribo GmbH",
                "+49 000 000 0000",
                "haribo@sweet.com",
                "android.resource://eu.laramartin.inventorymanager/drawable/smurfs");
        dbHelper.insertItem(smurfs);

        Inventory fresquito = new Inventory(
                "Fresquito",
                "9 €",
                54,
                "Fiesta S.A.",
                "+34 000 000 0000",
                "fiesta@dulce.com",
                "android.resource://eu.laramartin.inventorymanager/drawable/fresquito");
        dbHelper.insertItem(fresquito);

        Inventory hotChillies = new Inventory(
                "Hot chillies",
                "13 €",
                12,
                "Fiesta S.A.",
                "+34 000 000 0000",
                "fiesta@dulce.com",
                "android.resource://eu.laramartin.inventorymanager/drawable/hot_chillies");
        dbHelper.insertItem(hotChillies);

        Inventory lolipopStrawberry = new Inventory(
                "Lolipop strawberry",
                "12 €",
                62,
                "Fiesta S.A.",
                "+34 000 000 0000",
                "fiesta@dulce.com",
                "android.resource://eu.laramartin.inventorymanager/drawable/lolipop");
        dbHelper.insertItem(lolipopStrawberry);

        Inventory heartGummy = new Inventory(
                "Heart gummy jellies",
                "13 €",
                22,
                "Fiesta S.A.",
                "+34 000 000 0000",
                "fiesta@dulce.com",
                "android.resource://eu.laramartin.inventorymanager/drawable/heart_gummy");
        dbHelper.insertItem(heartGummy);
    }
}
