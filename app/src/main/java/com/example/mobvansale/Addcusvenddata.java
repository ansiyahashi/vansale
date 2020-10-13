package com.example.mobvansale;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Addcusvenddata extends DatabaseHandlerController {


    public static final String TABLE_NAME = "Customer_vendor";

    public static final String Column_Id = "CV_ID";
    public static final String Column_cvname  = "CV_NAME";
    public static final String Column_shname  = "SH_NAME";



    public static final String Column_address = "ADDRESS";
    public static final String Column_phno= "PH_NO";
    public static final String Column_email = "EMAIL";
    public static final String Column_creditbalance  = "CREDIT_BALANCE";
    public static final String Column_rid  = "R_ID";
    public static final String Column_status  = "STATUS";
    public static final String Column_discount  = "DISCOUNT";
    private final Context context;


    private DatabaseHandler dbhelper;
    private SQLiteDatabase sqliteDB;

    public Addcusvenddata(Context context) {
        this.context = context;
    }

    public void insert(Addcusvenddatamodel addcusvenddatamodel) {
        dbhelper = DatabaseHandler.getInstance(context);
        sqliteDB = dbhelper.getWritableDatabase();
        sqliteDB.beginTransaction();




        try {
            String[] fields_ar = { Column_cvname, Column_shname, Column_address,
                    Column_phno, Column_email,Column_creditbalance,Column_rid,Column_status,Column_discount};

            Object[] values_ar = new Object[]{addcusvenddatamodel.getcvname(),
                    addcusvenddatamodel.getshname(), addcusvenddatamodel.getaddress(), addcusvenddatamodel.getphno(), addcusvenddatamodel.getemail(), addcusvenddatamodel.getcreditbalance(), addcusvenddatamodel.getrid(), addcusvenddatamodel.getstatus(), addcusvenddatamodel.getdiscount()};
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


