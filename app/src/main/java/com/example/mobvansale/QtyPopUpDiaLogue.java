package com.example.mobvansale;


import android.app.Dialog;
import android.os.Bundle;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


public class QtyPopUpDiaLogue extends DialogFragment {

    View view;
    AlertDialog alertDialog;
    View.OnClickListener onClickListener;
    private OncompletCallback callBack;
    private EditText qtyedittext;
    private EditText priceedittext;
    private Button ok;
    private Button cancel;
    private int qty;
    private String price;

    public QtyPopUpDiaLogue()
    {
    }


    public static QtyPopUpDiaLogue newInstance(int qty, String price){
        QtyPopUpDiaLogue frag = new QtyPopUpDiaLogue();
        Bundle args = new Bundle();
        args.putString("price",price);
        args.putInt("qty",qty);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        price=getArguments().getString("price");

         qty=(int) getArguments().getInt("Qty");

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = view.getId();
                if (i == R.id.ok) {
                    if (qtyedittext.getText().toString().isEmpty()) {
                        qtyedittext.setError("Enter Qty");
                    } else {


                        alertDialog.dismiss();
                        if (getTargetFragment() != null && getTargetFragment() instanceof OncompletCallback) {
                            ((OncompletCallback) getTargetFragment()).onQtyclick(CommonUtils.toInt(qtyedittext.getText().toString()),
                                    priceedittext.getText().toString());
                        } else if (getActivity() instanceof OncompletCallback) {
                            ((OncompletCallback) getActivity()).onQtyclick(CommonUtils.toInt(qtyedittext.getText().toString()),
                                    priceedittext.getText().toString());
                        }
                    }

                } else if (i == R.id.cancel) {
                    alertDialog.dismiss();

                }
            }
        };
        //context=getActivity().getApplicationContext();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        view=getActivity().getLayoutInflater().inflate(R.layout.qty_popup_dialogue, null);
        priceedittext =(EditText) view.findViewById(R.id.price);
        qtyedittext =(EditText) view.findViewById(R.id.qtyedit);
        priceedittext.setText(price);
        qtyedittext.setText(CommonUtils.toString(qty));





        ok=(Button)view.findViewById(R.id.ok);
        cancel=(Button)view.findViewById(R.id.cancel);
        ok.setOnClickListener(onClickListener);
        cancel.setOnClickListener(onClickListener);
        alertDialogBuilder.setView(view);
        alertDialog=alertDialogBuilder.create();
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return alertDialog;
    }

}