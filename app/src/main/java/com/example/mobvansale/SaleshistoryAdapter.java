package com.example.mobvansale;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SaleshistoryAdapter extends RecyclerView.Adapter<SaleshistoryAdapter.ViewHolder>{
    List<SalesLineModel> listdata;
    OncompletCallback oncompletCallback;

    // RecyclerView recyclerView;
    public SaleshistoryAdapter(List<SalesLineModel> listdata,OncompletCallback oncompletCallback) {
        this.listdata = listdata;
        this.oncompletCallback=oncompletCallback;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.sales_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (position == 0) {
            holder.textView.setText("Customer name");
            holder.textView1.setText("Product name");
            holder.textView3.setText("Quantity");
            holder.textView4.setText("Price");

            holder.itemView.setBackgroundColor(Color.parseColor("#03DAC5"));

        } else {

            final SalesLineModel myListData = listdata.get(position-1);

            holder.textView.setText(myListData.getCustomerName());
            holder.textView1.setText(myListData.getProductName());
            //  holder.textView.setText(myListData.getPhno());
            holder.textView3.setText(myListData.getQty().toString());
            holder.textView4.setText(myListData.getAmount().toString());


            //holder.imageView.setImageResource(listdata[position].getImgId());
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    oncompletCallback.onclick(myListData);

                    Toast.makeText(view.getContext(), "click on item: " + myListData.getCustomerName(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return listdata.size()+1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView,textView1,textView3,textView4;
        public LinearLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);

            this.textView = (TextView) itemView.findViewById(R.id.textView);
            this.textView1 = (TextView) itemView.findViewById(R.id.textView1);
            this.textView3 = (TextView) itemView.findViewById(R.id.textView2);
            this.textView4 = (TextView) itemView.findViewById(R.id.textView3);

            relativeLayout = (LinearLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}
