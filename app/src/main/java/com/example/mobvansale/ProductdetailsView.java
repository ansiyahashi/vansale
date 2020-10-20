package com.example.mobvansale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ProductdetailsView extends AppCompatActivity implements OncompletCallback  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails_view);


        List<String> categories = new ArrayList<String>();
       Productinsertion productinsertion = new Productinsertion(this);
        List<Productinsertionmodel> productinsertionmodels=productinsertion.selectAllProducts();

        for (Productinsertionmodel productinsertionmodel:productinsertionmodels){
            categories.add(productinsertionmodel.getPname());
            categories.add(productinsertionmodel.getCatogry());

            categories.add(productinsertionmodel.getQty());

            categories.add(productinsertionmodel.getSrate());

            categories.add(productinsertionmodel.getPrate());
            categories.add(productinsertionmodel.getEdate());


        }




        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        ListofproductsAdapter adapter = new ListofproductsAdapter(productinsertionmodels,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onclick(Object data) {
        Productinsertionmodel productinsertionmodel=(Productinsertionmodel) data;
        Intent intent=new Intent(this, Homeowner.class);

        startActivity(intent);
    }

    @Override
    public void onQtyclick(int qty, String price) {

    }
}
