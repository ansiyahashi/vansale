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


public class SalesLineAdappter extends RecyclerView.Adapter<SalesLineAdappter.ViewHolder>{
    List<SalesLineModel> listdata;

    // RecyclerView recyclerView;
    public SalesLineAdappter(List<SalesLineModel> listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.sales_item_ist, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (position == 0) {
            holder.price.setText("Price");
            holder.qty.setText("QTY");
            holder.name.setText("Product Name");
            holder.slno.setText("SlNo");
            holder.total.setText("Total");
        } else {
            final SalesLineModel myListData = listdata.get(position-1);
            holder.price.setText(myListData.getAmount().toString());
            holder.qty.setText(myListData.getQty().toString());
            holder.name.setText(myListData.getProductName());
            holder.slno.setText(CommonUtils.toString(position));
            BigDecimal total=myListData.getAmount().multiply(myListData.getQty());
            holder.total.setText(total!=null?total.toString():"0.00");

            //holder.imageView.setImageResource(listdata[position].getImgId());
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    //Toast.makeText(view.getContext(),"click on item: "+myListData.getCvname(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return listdata.size()+1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,price,qty,slno,total;
        public LinearLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.productname);
            this.price = (TextView) itemView.findViewById(R.id.price);
            this.qty = (TextView) itemView.findViewById(R.id.qty);
            this.slno = (TextView) itemView.findViewById(R.id.slno);
            this.total = (TextView) itemView.findViewById(R.id.total);
            relativeLayout = (LinearLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}
