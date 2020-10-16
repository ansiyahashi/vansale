package com.example.mobvansale;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mobvansale.db";
    private static final int DATABASE_VERSION = 1;
    private final String TAG = "DatabaseHandler";
    private final Context context;


    private DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbClientCount = new AtomicInteger(0);
        Log.d(TAG, "Database: '" + DATABASE_NAME + "' Version: '" + DATABASE_VERSION + "'");
        upgraded = false;

    }

    AtomicInteger dbClientCount;
    boolean upgraded;

    static DatabaseHandler instance;

    public static synchronized DatabaseHandler getInstance(Context context) {
        if (instance == null) {
            try {
                instance = new DatabaseHandler(context);
            } catch (Exception e) {
                //ErrorMsg.showError(context,"Error While Creating Database. Contact Customer Service",e , "DB");
                Log.e("DB", "Error while setting up database. " + e.getMessage(), e);
            }
        }
        instance.registerConnection();
        return instance;
    }

    // @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        // db.disableWriteAheadLogging();
    }


    public static synchronized void reload() {
        if (instance != null) {
            try {
                instance.close();
            } catch (Exception ex) {
                Log.e("DB", "Unable to close database. " + ex.getMessage(), ex);
            }
            instance = null;
        } // new instance will be created on next call
    }

    @Override
    public void close() {
        unregisterConnection();
    }


    // Synchronization should be done same object for register & unregister. Hence use instance methods
    private synchronized void registerConnection() {
        int count = instance.dbClientCount.incrementAndGet();
        if (count > 49) // Print only when it goes out of control
            Log.w("DB", "New database connection request. Connection Count=" + count);

    }

    // Synchronization should be done same object for register & unregister. Hence use instance methods
    private synchronized void unregisterConnection() {
        int count = dbClientCount.decrementAndGet();
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Creating new Database: '" + DATABASE_NAME + "' Version: '" + DATABASE_VERSION + "'");
        try {
            db.beginTransaction();
            db.execSQL("CREATE TABLE IF NOT EXISTS registration(  ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , NAME TEXT," +
                    " ADDRESS TEXT , EMAIL TEXT , PHNO TEXT , PASSWORD TEXT )");


            db.execSQL("CREATE TABLE IF NOT EXISTS Route(  R_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,R_NAME TEXT," +
                    " LANDMARK TEXT )");



            db.execSQL("CREATE TABLE IF NOT EXISTS Customer_vendor(  CV_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , CV_NAME TEXT, SH_NAME TEXT,CUST_VEND TEXT,"+
                   " ADDRESS TEXT ,PH_NO TEXT, EMAIL TEXT ,CREDIT_BALANCE INTEGER,  R_ID INTEGER  ,STATUS INTEGER,DISCOUNT INTEGER )");



           db.execSQL("CREATE TABLE IF NOT EXISTS Product(  P_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , P_NAME TEXT," +
                    " CATOGORY TEXT ,C_QTY INTEGER, S_PRICE INTEGER , P_PRICE INTEGER,EXPRY_DATE" +
                   " TEXT )");

            db.execSQL("CREATE TABLE IF NOT EXISTS Product(  P_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , P_NAME TEXT," +
                    " CATOGORY TEXT ,C_QTY INTEGER, S_PRICE INTEGER , P_PRICE INTEGER,EXPRY_DATE" +
                    " TEXT )");


              db.execSQL("CREATE TABLE IF NOT EXISTS SalesOrder (  id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , customerid INTEGER," +
             " customerName TEXT , DISCOUNT INTEGER , isSales INTEGER , STATUS TEXT  )");

            db.execSQL("CREATE TABLE IF NOT EXISTS OrderLines (  id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , orderId INTEGER," +
                    " productId INTEGER , productName TEXT , qty INTEGER , amount INTEGER  )");


            db.setTransactionSuccessful();


        } catch (Exception e) {
            //ErrorMsg.showError( context, "Internal Error", e,"Error");
            Log.e("DB", "Error while setting up database. " + e.getMessage(), e);
        } finally {
            db.endTransaction();
        }
        onUpgrade(db, 1, DATABASE_VERSION);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version  " + oldVersion
                + " to " + newVersion + "");
        int upgradeTo = oldVersion + 1;
        while (upgradeTo <= newVersion) {
            Log.w(TAG, "Upgrading database to version: " + upgradeTo);
            switch (upgradeTo) {
                case 1:
                    onCreate(db);
                    break;
            }
            Log.w(TAG, "Upgraded database to version: " + upgradeTo);
            upgradeTo++;
        }
        // Views cannot be altered. Hence drop and create again // This should be done last
        upgraded = true;
    }
}