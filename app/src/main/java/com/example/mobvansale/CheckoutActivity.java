package com.example.mobvansale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CheckoutActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText total,discount,oldbalance,newbalance,creditbalance,amountpaid;
    Spinner spinner;
    EditText view;
    Button button;
    List<String> paymentType;
    private String selectedType;
    java.math.BigDecimal tcdotal;
    java.math.BigDecimal creditbal;
    java.math.BigDecimal Discount;
    java.math.BigDecimal tot;

    int customerID;
    int OrderId;
    OrderDto salesorder;
    java.math.BigDecimal newBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Intent intent = getIntent();
         OrderId = ((intent != null) && intent.hasExtra("orderid")) ? intent.getIntExtra("orderid", 0) : 0;
         customerID = ((intent != null) && intent.hasExtra("customerId")) ? intent.getIntExtra("customerId", 0) : 0;
        String grantTotal = ((intent != null) && intent.hasExtra("grandtotal")) ? intent.getStringExtra("grandtotal"):"";

        final Addcusvenddata customer=new Addcusvenddata(getApplicationContext());
        Addcusvenddatamodel customwerModel=customer.getCustomer(customerID);
        salesorder=new OrderDto(getApplicationContext());
        total=(EditText)findViewById(R.id.total);
        discount=(EditText)findViewById(R.id.discount);
        view=(EditText)findViewById(R.id.view);
        oldbalance=(EditText)findViewById(R.id.oldbalance);
        newbalance=(EditText)findViewById(R.id.newbalance);
        creditbalance=(EditText)findViewById(R.id.creditbalance);
        amountpaid=(EditText)findViewById(R.id.amountPAid);
        spinner=(Spinner) findViewById(R.id.spinner);

        total.setText(grantTotal);
        discount.setText(customwerModel.getDiscount());
        oldbalance.setText(customwerModel.getCreditBalance());

        if (grantTotal!=null){
            tcdotal= CommonUtils.toBigDecimal(grantTotal);
        }else {
            tcdotal= java.math.BigDecimal.ZERO;
        }
        if (customwerModel!=null&& customwerModel.getCreditBalance()!=null){
            creditbal= CommonUtils.toBigDecimal(customwerModel.getCreditBalance());
        }else {
            creditbal= java.math.BigDecimal.ZERO;
        }
        Discount=CommonUtils.toBigDecimal(customwerModel.getDiscount());
        tot=tcdotal;
        tot=tcdotal.multiply(Discount.divide(BigDecimal.valueOf(100)));
tcdotal=tot.add(tcdotal);
//tcdotal.multiply(Discount.divide(BigDecimal.valueOf(100)));
        newBalance=creditbal.add(tcdotal);
        newbalance.setText(newBalance.toString());
        creditbalance.setText(newBalance.toString());


        spinner.setOnItemSelectedListener(CheckoutActivity.this);

        // Spinner Drop down elements
       paymentType = new ArrayList<String>();

        paymentType.add("Cash");
        paymentType.add("Credit");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, paymentType);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        button=(Button)findViewById(R.id.checkout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creditbal.add(CommonUtils.toBigDecimal(amountpaid.getText().toString())).toString();
                if (selectedType=="Credit"){
                    customer.update(newBalance.toPlainString(),customerID);
                }else {
                    customer.update((creditbal.add(tcdotal)).subtract(CommonUtils.toBigDecimal(amountpaid.getText().toString())).toString(),customerID);
                }


                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                salesorder.updatedate(date,OrderId);
                salesorder.update("C", OrderId);
                Toast.makeText(getApplicationContext(),"SALES Completed",Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedType = paymentType.get(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}