package com.example.emuveyanfarmsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Sales sales;
    private Purchase purchase;

    public static final String EXTRA_TEXT = "com.example.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.example.EXTRA_NUMBER";

    public static final String EXTRA_TEXT2 = "com.example.EXTRA_TEXT2";
    public static final String EXTRA_NUMBER2 = "com.example.EXTRA_NUMBER2";

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sales = new Sales();
        purchase = new Purchase();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, sales)
                .replace(R.id.fragment_container, purchase)  //possible mistake here
                .commit();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Sales()).commit();
            navigationView.setCheckedItem(R.id.sales);
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sales:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Sales()).commit();
                break;
            case R.id.purchase:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Purchase()).commit();
                break;
            case R.id.records:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Records()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    public void openRecords(){
        EditText editText1 = (EditText) findViewById(R.id.salesitem);
        String text1 = editText1.getText().toString();

        EditText editText2 = (EditText) findViewById(R.id.prizeofsales);
        int number1 = Integer.parseInt(editText2.getText().toString());

        EditText editText3 = (EditText) findViewById(R.id.purchaseitem);
        String text2 = editText3.getText().toString();

        EditText editText4 = (EditText) findViewById(R.id.prizeofpurchase);
        int number2 = Integer.parseInt(editText4.getText().toString());

        Intent intent = new Intent(this,Records.class);
        intent.putExtra(EXTRA_TEXT, text1);
        intent.putExtra(EXTRA_NUMBER, number1);

        intent.putExtra(EXTRA_TEXT2, text2);
        intent.putExtra(EXTRA_NUMBER2, number2);
        startActivity(intent);
    }

    public void onInputSalesSent(CharSequence input) {
        sales.updateEditText1(input);
    }

    public void onInputSalesSent(CharSequence input1) {
        sales.updateEditText2(input1);

    }



}