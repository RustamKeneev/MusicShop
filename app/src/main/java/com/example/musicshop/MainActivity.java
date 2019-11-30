package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private AppCompatSpinner spinner;
    private TextView increase_quantity;
    private int quantity = 0;

    private ArrayList spinnerArrayList;
    private ArrayAdapter spinnerAdapter;
    private HashMap hashMap;
    private String namesProducts;
    private double price;
    private TextView  result;
    private ImageView instrumental_imageView;
    private EditText editTextUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewGroup();
        creatSpiner();
        creatHaspMap();

    }

    private void creatHaspMap() {
        hashMap = new HashMap();
        hashMap.put("guitar",500.0);
        hashMap.put("drums",700.0);
        hashMap.put("keyboard",400.0);
    }

    private void initViewGroup() {
        increase_quantity = findViewById(R.id.quatity_result);
        result = findViewById(R.id.order_result);
        instrumental_imageView = findViewById(R.id.instrumental_imageView);
        spinner = findViewById(R.id.spinner);
        editTextUserName = findViewById(R.id.editText);
    }

    private void creatSpiner() {
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("guitar");
        spinnerArrayList.add("drums");
        spinnerArrayList.add("keyboard");
        spinnerAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    public void increase_quantity(View view) {
        quantity = quantity +1;
        increase_quantity.setText("" + quantity);
        result.setText(" "+ quantity * price);
    }

    public void decreaseQuantity(View view) {
    quantity = quantity -1;
    if (quantity <0){
        quantity =0;
    }
    increase_quantity.setText(""+quantity);
    result.setText(" "+ quantity * price);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        namesProducts = spinner.getSelectedItem().toString();
        price = (double)hashMap.get(namesProducts);
        result.setText(" "+ quantity * price);
        switch (namesProducts){
            case "guitar":instrumental_imageView.setImageResource(R.drawable.guitar);
                break;
            case "drums":instrumental_imageView.setImageResource(R.drawable.drums);
                break;
            case "keyboard":instrumental_imageView.setImageResource(R.drawable.keyboard);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void add_to_card(View view) {
        Order order = new Order();
        order.userName = editTextUserName.getText().toString();
        Log.d("ololo", order.userName );
        order.producrName = namesProducts;
        Log.d("ololo", order.producrName);
        order.quantity = quantity;
        Log.d("ololo", "quantity"+order.quantity);
        order.orderPrice = quantity * price;
        Log.d("ololo", "orderPrice"+order.orderPrice);

        Intent orderIntent = new Intent(MainActivity.this,OrderActivity.class);
        orderIntent.putExtra("userNamePutExtra",order.userName);
        orderIntent.putExtra("userproducrName",order.producrName);
        orderIntent.putExtra("userquantity",order.quantity);
        orderIntent.putExtra("userorderPrice",order.orderPrice);
        startActivity(orderIntent);
    }
}
