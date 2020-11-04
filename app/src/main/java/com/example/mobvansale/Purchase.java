package com.example.mobvansale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Purchase extends AppCompatActivity implements OncompletCallback {
    OrderDto salesorder;
    int OrderId,customerID;
    Button submit;
    TextView txtgrandTotal;
    String grandTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        submit=(Button)findViewById(R.id.submit);
        txtgrandTotal=(TextView) findViewById(R.id.grandtotal);
        Intent intent = getIntent();
        OrderId = ((intent != null) && intent.hasExtra("orderid")) ? intent.getIntExtra("orderid", 0) : 0;
        customerID = ((intent != null) && intent.hasExtra("customerId")) ? intent.getIntExtra("customerId", 0) : 0;

        RecyclerView recyclerViewProductLidt = (RecyclerView) findViewById(R.id.recyclerview_productList);

        Productinsertion products = new Productinsertion(this);
        List<Productinsertionmodel> productinsertionmodelList=products.selectAllProducts();
        ProductLineAdapter adapter = new ProductLineAdapter(productinsertionmodelList,this);
        recyclerViewProductLidt.setHasFixedSize(true);
        recyclerViewProductLidt.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProductLidt.setAdapter(adapter);

        setlines();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Purchase.this, PaymentActivity.class);
                intent.putExtra("orderid",OrderId);
                intent.putExtra("grandtotal",grandTotal);
                intent.putExtra("customerId",customerID);
                // intent.putExtra("customername",addcusvenddatamodel.getCvname());
                // intent.putExtra("discount",addcusvenddatamodel.getDiscount());
                startActivity(intent);

                //salesorder.update("C");
                //Toast.makeText(getApplicationContext(),"Order Completed",Toast.LENGTH_SHORT).show();
                //finish();
            }
        });
    }

    private void setlines() {

        RecyclerView recyclerViewlines = (RecyclerView) findViewById(R.id.recyclerview_saleslines);
        OrderLines orderLines = new OrderLines(this);
        List<SalesLineModel> salesLineModels=orderLines.selectAllLines(OrderId);
        SalesLineAdappter adapter1 = new SalesLineAdappter(salesLineModels);
        recyclerViewlines.setHasFixedSize(true);
        recyclerViewlines.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewlines.setAdapter(adapter1);
        grandTotal=orderLines.getSumOfLines(OrderId);
        txtgrandTotal.setText(grandTotal);
    }

    @Override
    public void onclick(Object data) {

    }

    @Override
    public void onQtyclick(int qty, String price) {

    }
}

