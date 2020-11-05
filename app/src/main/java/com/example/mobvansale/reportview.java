package com.example.mobvansale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class reportview extends AppCompatActivity {
String d1,d2;
EditText sales,totcollection,purchase,totpayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportview);
sales=(EditText)findViewById(R.id.SALES) ;

        totcollection=(EditText)findViewById(R.id.TOTALCOLLECTION) ;
        purchase=(EditText)findViewById(R.id.PURCHASE) ;
        totpayment=(EditText)findViewById(R.id.TOTALPAYMENT) ;
        Intent intent = getIntent();

        d1 = getIntent().getStringExtra("startdate");
       // d2 = getIntent().getStringExtra("enddate");
        OrderDto orderDto = new OrderDto(this);
      //  List<String> categories = new ArrayList<String>();
        String sales1= orderDto.selectsales(d1);
sales.setText(sales1);
        String totalcollectn= orderDto.selecttotalcollection(d1);
        totcollection.setText(totalcollectn);

        String purchase1= orderDto.selectpurchase(d1);
        purchase.setText(purchase1);


        String totalpayment= orderDto.selecttotalpayment(d1);
        totpayment.setText(totalpayment);




    }
       // d2 = ((intent != null) && intent.hasExtra("value")) ? intent.getStringExtra("value") :"" ;
       // OrderLines orderLines = new OrderLines(getApplicationContext());
     //List<SalesLineModel> orderModels=orderLines.selectsalesreport(d1,d2);

    }
