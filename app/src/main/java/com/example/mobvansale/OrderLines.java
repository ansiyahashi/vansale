package com.example.mobvansale;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderLines  extends DatabaseHandlerController{
    public static final String TABLE_NAME = "OrderLines";

    public static final String Column_Id = "id";
    public static final String orderId  = "orderId";
    public static final String productId  = "productId";
    public static final String productName  = "productName";
    public static final String qty = "qty";
    public static final String amount = "amount";
    private final Context context;

    private DatabaseHandler dbhelper;
    private SQLiteDatabase sqliteDB;

    public OrderLines(Context context) {
        this.context = context;
    }

    public void insert(SalesLineModel productinsertionmodel) {
        dbhelper = DatabaseHandler.getInstance(context);
        sqliteDB = dbhelper.getWritableDatabase();
        sqliteDB.beginTransaction();




        try {
            String[] fields_ar = { orderId, productId, productName,
                    qty, amount};

            Object[] values_ar = {productinsertionmodel.getOrderId(), productinsertionmodel.getProductId(), productinsertionmodel.getProductName(),
                    productinsertionmodel.getQty(),productinsertionmodel.getAmount()};
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
    public String getSumOfLines(int order_Id) {


        String query = "select sum(amount*qty) from " + TABLE_NAME+" where orderId="+order_Id; ;

        ArrayList<ArrayList<String>> result = super.executeQuery(context, query);
        if (result!=null){
            return result.get(0).get(0);
        }else {
            return " 0.00";
        }

    }

    public List<SalesLineModel>  selectAllLines(int order_Id) {


        String query = "select * from " + TABLE_NAME+" where orderId="+order_Id; ;

        ArrayList<ArrayList<String>> result = super.executeQuery(context, query);

        return prepareModel(result);
    }


    private List<SalesLineModel> prepareModel(ArrayList<ArrayList<String>> data  ) {


        List<SalesLineModel> addcusvenddatamodels= new ArrayList<>();
        for (ArrayList<String> tuple : data) {
            SalesLineModel temp = new SalesLineModel();

            temp.setId(CommonUtils.toInt(tuple.get(0)));
            temp.setOrderId(CommonUtils.toInt(tuple.get(1)));
            temp.setProductId(CommonUtils.toInt(tuple.get(2)));
            temp.setProductName(tuple.get(3));
            temp.setQty(CommonUtils.toBigDecimal(tuple.get(4)));
            temp.setAmount(CommonUtils.toBigDecimal(tuple.get(5)));
            addcusvenddatamodels.add(temp);
        }
        return addcusvenddatamodels;
    }



}


