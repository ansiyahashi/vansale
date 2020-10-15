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
    public static final String Column_cust_vend  = "CUST_VEND ";


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
            String[] fields_ar = { Column_cvname, Column_shname, Column_cust_vend, Column_address,
                    Column_phno, Column_email,Column_creditbalance,Column_rid,Column_status,Column_discount};

            Object[] values_ar = new Object[]{addcusvenddatamodel.getCvname(),
                    addcusvenddatamodel.getShname(),addcusvenddatamodel.getCust_vend(),addcusvenddatamodel.getAddress(), addcusvenddatamodel.getPhno(), addcusvenddatamodel.getEmail(), 0 , addcusvenddatamodel.getRid(), 1 ,addcusvenddatamodel.getDiscount()};
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
    public List<Addcusvenddatamodel>  selectAllCustomers() {


        String query = "select * from " + TABLE_NAME +" where "+Column_cust_vend+" = 'customer'";

        ArrayList<ArrayList<String>> result = super.executeQuery(context, query);

        return prepareModel(result);
    }

    public List<Addcusvenddatamodel>  selectAllVendors() {


        String query = "select * from " + TABLE_NAME +" where "+Column_cust_vend+" = 'vendor'";

        ArrayList<ArrayList<String>> result = super.executeQuery(context, query);

        return prepareModel(result);
    }


    private List<Addcusvenddatamodel> prepareModel(ArrayList<ArrayList<String>> data  ) {


        List<Addcusvenddatamodel> addcusvenddatamodels= new ArrayList<>();
        for (ArrayList<String> tuple : data) {
            Addcusvenddatamodel temp = new Addcusvenddatamodel();

            temp.setId(CommonUtils.toInt(tuple.get(0)));
            temp.setCvname(tuple.get(1));
            temp.setShname(tuple.get(2));
            temp.setCust_vend(tuple.get(3));
            temp.setAddress(tuple.get(4));
            temp.setAddress(tuple.get(5));
            temp.setEmail(tuple.get(6));
            temp.setPhno(tuple.get(7));
            temp.setRid(tuple.get(8));
            temp.setDiscount(tuple.get(9));
            addcusvenddatamodels.add(temp);
        }
        return addcusvenddatamodels;
    }



}


