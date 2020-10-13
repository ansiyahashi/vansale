package com.example.mobvansale;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class registration extends DatabaseHandlerController {

    public static final String TABLE_NAME = "registration";
    private final Context context;

    public static final String Column_Id = "ID";
    public static final String Column_name  = "NAME";
    public static final String Column_address  = "ADDRESS";

    public static final String Column_email  = "EMAIL";
    public static final String Column_phno = "PHNO";
    public static final String Column_password  = "PASSWORD";

    private DatabaseHandler dbhelper;
    private SQLiteDatabase sqliteDB;

    public registration(Context context) {
        this.context = context;
    }

    public void insert(RegistrationModel registrationModel) {
        dbhelper = DatabaseHandler.getInstance(context);
        sqliteDB = dbhelper.getWritableDatabase();
        sqliteDB.beginTransaction();

        try {
            String[] fields_ar = { Column_name, Column_address, Column_email,
                    Column_phno, Column_password};

            Object[] values_ar = { registrationModel.getName(),
                    registrationModel.getAddress(), registrationModel.getEmail(), registrationModel.getPhno(), registrationModel.getPassword()};
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
    public RegistrationModel  selectAllFavouriteProducts() {


        String query = "select * from " + TABLE_NAME;

        ArrayList<ArrayList<String>> result = super.executeQuery(context, query);
        if (result!=null)
            return prepareModel(result).get(0);
        return null;
    }

    private List<RegistrationModel> prepareModel(ArrayList<ArrayList<String>> data  ) {


        List<RegistrationModel> registrationModels = new ArrayList<>();
        for (ArrayList<String> tuple : data) {
            RegistrationModel temp = new RegistrationModel();

            temp.setId(CommonUtils.toInt(tuple.get(0)));
            temp.setName(tuple.get(1));

            registrationModels.add(temp);
        }
        return registrationModels;
    }



}
