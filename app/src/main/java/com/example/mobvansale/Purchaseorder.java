package com.example.mobvansale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Purchaseorder extends AppCompatActivity implements OncompletCallback {
    int OrderId;
    QtyPopUpDiaLogue dialog;
    OrderLines orderLines;
    OrderDto salesorder;
    String customerName;
    int discount;
    int customerId;
    Productinsertionmodel selectedProduct;
    Button submit;
    TextView grandTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_order);
        submit=(Button)findViewById(R.id.submit);
        grandTotal=(TextView) findViewById(R.id.grandtotal);

        orderLines=new OrderLines(getApplicationContext());
        salesorder=new OrderDto(getApplicationContext());
        OrderModel orderModel =new OrderModel();
        orderModel.setSales(false);
        orderModel.setDISCOUNT(discount);
        orderModel.setCustomerName(customerName);
        orderModel.setCustomerid(customerId);
        orderModel.setSTATUS("D");
        salesorder.insert(orderModel);


        OrderId=salesorder.getNewDocId();


        RecyclerView recyclerViewProductLidt = (RecyclerView) findViewById(R.id.recyclerview_productList);

        Productinsertion products = new Productinsertion(this);
        List<Productinsertionmodel> productinsertionmodelList=products.selectAllProducts();
        ProductpurchaseLineAdapter adapter = new ProductpurchaseLineAdapter(productinsertionmodelList,this);
        recyclerViewProductLidt.setHasFixedSize(true);
        recyclerViewProductLidt.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProductLidt.setAdapter(adapter);

        setlines();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salesorder.update("C");
                Toast.makeText(getApplicationContext(),"Order Completed",Toast.LENGTH_SHORT).show();
                finish();
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

        grandTotal.setText(orderLines.getSumOfLines(OrderId));
    }

    @Override
    public void onclick(Object data) {
        selectedProduct=(Productinsertionmodel)data;

        dialog = QtyPopUpDiaLogue.newInstance(1,selectedProduct.getPrate());
        dialog.show(getSupportFragmentManager(),"");


    }

    @Override
    public void onQtyclick(int qty, String price) {
        SalesLineModel salesLineModel=new SalesLineModel();
        salesLineModel.setOrderId(OrderId);
        salesLineModel.setAmount(CommonUtils.toBigDecimal(price));
        salesLineModel.setProductName(selectedProduct.getPname());
        salesLineModel.setQty(CommonUtils.toBigDecimal(CommonUtils.toString(qty)));
        salesLineModel.setProductId(selectedProduct.getId());
        orderLines.insert(salesLineModel);
        setlines();
    }
}