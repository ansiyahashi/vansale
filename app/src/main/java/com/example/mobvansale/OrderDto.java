package com.example.mobvansale;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OrderDto  extends DatabaseHandlerController{
    public static final String TABLE_NAME = "SalesOrder";

    public static final String Column_Id = "id";
    public static final String customerid  = "customerid";
    public static final String customerName  = "customerName";
    public static final String DISCOUNT  = "DISCOUNT";
    public static final String isSales = "isSales";
    public static final String STATUS = "STATUS";
    private final Context context;

    private DatabaseHandler dbhelper;
    private SQLiteDatabase sqliteDB;

    public OrderDto(Context context) {
        this.context = context;
    }



    public void insert(OrderModel productinsertionmodel) {
        dbhelper = DatabaseHandler.getInstance(context);
        sqliteDB = dbhelper.getWritableDatabase();
        sqliteDB.beginTransaction();




        try {
            String[] fields_ar = { customerid, customerName, DISCOUNT,
                    isSales, STATUS};

            Object[] values_ar = {productinsertionmodel.getCustomerid(), productinsertionmodel.getCustomerName(),
                    productinsertionmodel.getDISCOUNT(),
                    productinsertionmodel.isSales()?"1":"0",productinsertionmodel.getSTATUS()};
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


    public List<OrderModel>  selectAllLines(int id) {


        String query = "select * from " + TABLE_NAME+" where orderId="+id; ;

        ArrayList<ArrayList<String>> result = super.executeQuery(context, query);

        return prepareModel(result);
    }


    public  void update(String c) {
        String sql="UPDATE "+TABLE_NAME+" set STATUS ="+CommonUtils.quoteString(c);
        super.execute(context, sql);
    }

    private List<OrderModel> prepareModel(ArrayList<ArrayList<String>> data  ) {


        List<OrderModel> addcusvenddatamodels= new ArrayList<>();
        for (ArrayList<String> tuple : data) {
            OrderModel temp = new OrderModel();

            temp.setColumn_Id(CommonUtils.toInt(tuple.get(0)));
            temp.setColumn_Id(CommonUtils.toInt(tuple.get(1)));
            temp.setCustomerName(tuple.get(2));
            temp.setDISCOUNT(CommonUtils.toInt(tuple.get(3)));
            int isSales=CommonUtils.toInt(tuple.get(4));
            temp.setSales(isSales==1);
            temp.setSTATUS(tuple.get(5));
            addcusvenddatamodels.add(temp);
        }
        return addcusvenddatamodels;
    }



        public int getNewDocId() {
            ArrayList<ArrayList<String>> result=super.executeQuery(context,"SELECT * FROM "+TABLE_NAME+
                    " ORDER BY id DESC LIMIT 1");
            String recordId=result.size()>0?result.get(0).get(0):"0";
            return  CommonUtils.toInt(recordId);
        }

}


