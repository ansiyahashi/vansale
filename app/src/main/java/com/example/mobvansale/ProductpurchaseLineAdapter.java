package com.example.mobvansale;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import java.math.BigDecimal;
import java.util.List;


public class ProductpurchaseLineAdapter extends RecyclerView.Adapter<ProductpurchaseLineAdapter.ViewHolder>{
    List<Productinsertionmodel> listdata;
    OncompletCallback oncompletCallback;

    // RecyclerView recyclerView;
    public ProductpurchaseLineAdapter(List<Productinsertionmodel> listdata,OncompletCallback oncompletCallback) {
        this.listdata = listdata;
        this.oncompletCallback=oncompletCallback;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.product_item_ist, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (position == 0) {
            holder.name.setText("Product Name");

            holder.price.setText("Price");



        } else {
            final Productinsertionmodel myListData = listdata.get(position);

            //  holder.qty.setText(myListData.getQty());
            holder.name.setText(myListData.getPname());

            holder.price.setText(myListData.getPrate());


            //holder.imageView.setImageResource(listdata[position].getImgId());
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    oncompletCallback.onclick(myListData);
                }
            });
        }

    }
    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,price;
        public LinearLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.productname);
            this.price = (TextView) itemView.findViewById(R.id.price);

            relativeLayout = (LinearLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}
