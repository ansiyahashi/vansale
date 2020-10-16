package com.example.mobvansale;

public class OrderModel {

   int Column_Id;
    int customerid ;
     String customerName ;
     int DISCOUNT ;
    boolean isSales ;
    String STATUS ;

    public int getColumn_Id() {
        return Column_Id;
    }

    public void setColumn_Id(int column_Id) {
        Column_Id = column_Id;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getDISCOUNT() {
        return DISCOUNT;
    }

    public void setDISCOUNT(int DISCOUNT) {
        this.DISCOUNT = DISCOUNT;
    }

    public boolean isSales() {
        return isSales;
    }

    public void setSales(boolean sales) {
        isSales = sales;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
}
