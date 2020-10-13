package com.example.mobvansale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Homeowner extends AppCompatActivity {
    private Button addproduct;
    private Button salesorder;
    private Button purchaseorder;
    private Button addroute;
    private Button addcusvend;
    private Button saleshistory;
    private Button purchasehistory;
    private Button stockview;
    private Button report;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeowner);
        addproduct=(Button) findViewById(R.id.baddprdct);
        salesorder=(Button) findViewById(R.id.bsalesordr);
        purchaseorder=(Button) findViewById(R.id.bpurchsordr);
        addroute=(Button) findViewById(R.id.baddroute);
        addcusvend=(Button) findViewById(R.id.baddcusvend);
        saleshistory=(Button) findViewById(R.id.saleshistry);
        purchasehistory=(Button) findViewById(R.id.bpurchshstry);
        stockview=(Button) findViewById(R.id.bstockview);
        report=(Button) findViewById(R.id.breport);









        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler databaseHandler= DatabaseHandler.getInstance(getApplicationContext());
                addproduct();
            }
        });
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
        addroute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addroute();
            }
        });
        addcusvend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addcusvend();
            }
        });
        saleshistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saleshistory();
            }
        });
        purchasehistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                purchasehistory();
            }
        });
        stockview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stockview();
            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report();
            }
        });

    }

    public void addproduct()
    {
        Intent intent=new Intent(Homeowner.this, Addproduct.class);
        startActivity(intent);
    }
    public void salesorder()
    {
        Intent intent=new Intent(Homeowner.this, Salesorder.class);
        startActivity(intent);
    }
    public void purchaseorder()
    {
        Intent intent=new Intent(Homeowner.this, Purchaseorder.class);
        startActivity(intent);
    }
    public void addroute()
    {
        Intent intent=new Intent(Homeowner.this, Addroute.class);
        startActivity(intent);
    }
    public void addcusvend()
    {
        Intent intent=new Intent(Homeowner.this, Cusvend.class);
        startActivity(intent);
    }
    public void saleshistory()
    {
        Intent intent=new Intent(Homeowner.this, Saleshistory.class);
        startActivity(intent);
    }
    public void purchasehistory()
    {
        Intent intent=new Intent(Homeowner.this, Purchasehistory.class);
        startActivity(intent);
    }
    public void stockview()
    {
        Intent intent=new Intent(Homeowner.this, Stockview.class);
        startActivity(intent);
    }
    public void report()
    {
        Intent intent=new Intent(Homeowner.this, Report.class);
        startActivity(intent);
    }
}




