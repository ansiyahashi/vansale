package com.example.mobvansale;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Productinsertion  extends DatabaseHandlerController{
    public static final String TABLE_NAME = "Product";

    public static final String Column_Id = "P_ID";
    public static final String Column_pname  = "P_NAME";
    public static final String Column_catogory  = "CATOGORY";



    public static final String Column_qty  = "C_QTY";
    public static final String Column_sprice = "S_PRICE";
    public static final String Column_pprice = "P_PRICE";
    public static final String Column_date  = "EXPRY_DATE";
    private final Context context;

    private DatabaseHandler dbhelper;
    private SQLiteDatabase sqliteDB;

    public Productinsertion(Context context) {
        this.context = context;
    }

    public void insert(Productinsertionmodel productinsertionmodel) {
        dbhelper = DatabaseHandler.getInstance(context);
        sqliteDB = dbhelper.getWritableDatabase();
        sqliteDB.beginTransaction();




        try {
            String[] fields_ar = { Column_pname, Column_catogory, Column_qty,
                    Column_sprice, Column_pprice,Column_date};

            Object[] values_ar = { productinsertionmodel.getPname(),
                    productinsertionmodel.getCatogry(), productinsertionmodel.getQty(), productinsertionmodel.getSrate(), productinsertionmodel.getPrate(),productinsertionmodel.getEdate()};
            String values = "", fields = "";


            for (int i = 0; i < values_ar.length; i++) {
                if (values_ar[i] != null) {
                    values += CommonUtils.quoteIfString(values_ar[i]) + ",";
                    fields += fields_ar[i] + ",";
                }
            }

            if (!values.isEmpty()) {
                values = values.substring(0, values.length() - 1);
                fields = fields.substring(0, fields.length() - 1);
                String query = "INSERT INTO " + TABLE_NAME + "(" + fields + ") values(" + values + ");";
                Log.d("Favourite", query);
                sqliteDB.execSQL(query);
            }
            sqliteDB.setTransactionSuccessful();
        } catch (SQLException e) {
            //ErrorMsg.showError(context, "Error while running DB query", e, "");
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {

            sqliteDB.endTransaction();

            dbhelper.close();







        }

    }


}


