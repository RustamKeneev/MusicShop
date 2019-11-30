package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    private TextView orderTextView;
    private String[] addresses = {"dr.rustamkeneev@gmail.com"};
    private String subject = "Order from mobile application";
    private String emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        orderTextView = findViewById(R.id.order_text);
        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userNamePutExtra");
        String userproducrName = receivedOrderIntent.getStringExtra("userproducrName");
        int userquantity = receivedOrderIntent.getIntExtra("userquantity",0);
        double userorderPrice = receivedOrderIntent.getDoubleExtra("userorderPrice",0);
        emailText = "Customer name:"+userName + "\n" + "Name products: " +userproducrName +"\n"+
                "Price:" + userquantity + "\n"+ "Order price" + userorderPrice;
        orderTextView.setText(emailText);

    }

    public void button_add(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void composeEmail(String[] addresses, String subject) {

    }
}
