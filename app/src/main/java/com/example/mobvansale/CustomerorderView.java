package com.example.mobvansale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CustomerorderView extends AppCompatActivity implements OncompletCallback  {
    OrderLines orderLines;
    OrderDto salesorder;
    int OrderId;
    int cust_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerorder_view);
       // orderLines=new OrderLines(getApplicationContext());
        //salesorder=new OrderDto(getApplicationContext());
        //OrderId=salesorder.getNewDocId();
        List<String> categories = new ArrayList<String>();
        OrderDto orderDto = new OrderDto(this);
        Intent intent = getIntent();
        cust_id = ((intent != null) && intent.hasExtra("customerid")) ? intent.getIntExtra("customerid", 0) : 0;

        List<OrderModel> orderModels= orderDto.selectAllLines(cust_id);

        for (OrderModel orderModel:orderModels){
            categories.add(orderModel.getCustomerName());
            categories.add(orderModel.getSTATUS());



        }




        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.COrecyclerview);
        ListofcustomerorderAdapter adapter = new ListofcustomerorderAdapter(orderModels,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onclick(Object data) {
        OrderModel orderModel=(OrderModel) data;
        //Addcusvenddatamodel addcusvenddatamodel=(Addcusvenddatamodel)data;
        Intent intent=new Intent(this, Salesactivity.class);
        intent.putExtra("orderid",orderModel.getColumn_Id());
        intent.putExtra("customerId",cust_id);
       // intent.putExtra("customername",addcusvenddatamodel.getCvname());
       // intent.putExtra("discount",addcusvenddatamodel.getDiscount());
        startActivity(intent);
    }

    @Override
    public void onQtyclick(int qty, String price) {

    }
}
