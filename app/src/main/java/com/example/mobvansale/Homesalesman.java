package com.example.mobvansale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Homesalesman extends AppCompatActivity {
    private Button salesorder;
    private Button purchaseorder;

    private Button addcus;

    private Button stockview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homesalesman);
        salesorder=(Button)findViewById(R.id.bsalesordr);
        purchaseorder=(Button)findViewById(R.id.bpurchsordr);
        stockview=(Button)findViewById(R.id.bstockview);
        addcus=(Button)findViewById(R.id.baddcus);
        salesorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salesorder();
            }

        });

        purchaseorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                purchaseorder();
            }

        });

        stockview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stockview();
            }

        });

        addcus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addcus();
            }

        });
    }
    public void salesorder() {
        Intent intent=new Intent(Homesalesman.this, Smsalesorder.class);
        startActivity(intent);
    }
    public void purchaseorder() {
        Intent intent=new Intent(Homesalesman.this, Smpurchaseorder.class);
        startActivity(intent);
    }
    public void stockview() {
        Intent intent=new Intent(Homesalesman.this, Smstockview.class);
        startActivity(intent);
    }
    public void addcus() {
        Intent intent=new Intent(Homesalesman.this, Smaddcustomer.class);
        startActivity(intent);
    }
}