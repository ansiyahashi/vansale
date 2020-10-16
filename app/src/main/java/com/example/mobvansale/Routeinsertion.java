package com.example.mobvansale;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Routeinsertion extends DatabaseHandlerController {

    public static final String TABLE_NAME = "Route";
    private final Context context;

    public static final String Column_Id = "R_ID";
    public static final String Column_route  = "R_NAME";
    public static final String Column_landmark  = "LANDMARK";



    private DatabaseHandler dbhelper;
    private SQLiteDatabase sqliteDB;

    public Routeinsertion(Context context) {
        this.context = context;
    }

    public void insert(Routeinsertionmodel routeinsertionmodel) {
        dbhelper = DatabaseHandler.getInstance(context);
        sqliteDB = dbhelper.getWritableDatabase();
        sqliteDB.beginTransaction();

        try {
            String[] fields_ar = { Column_route, Column_landmark, };

            Object[] values_ar = { routeinsertionmodel.getRname(),
                   routeinsertionmodel.getLandmark()};
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
    public List<Routeinsertionmodel>  selectAllRoutes() {


        String query = "select * from " + TABLE_NAME;

        ArrayList<ArrayList<String>> result = super.executeQuery(context, query);

           return prepareModel(result);
    }

    private List<Routeinsertionmodel> prepareModel(ArrayList<ArrayList<String>> data  ) {


        List<Routeinsertionmodel> routeinsertionmodels= new ArrayList<>();
        for (ArrayList<String> tuple : data) {
            Routeinsertionmodel temp = new Routeinsertionmodel();

            temp.setId(CommonUtils.toInt(tuple.get(0)));
            temp.setRname(tuple.get(1));
            temp.setLandmark(tuple.get(2));

           routeinsertionmodels.add(temp);
        }
        return routeinsertionmodels;
    }



}


