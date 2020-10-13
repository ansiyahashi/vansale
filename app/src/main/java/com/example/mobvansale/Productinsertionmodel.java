package com.example.mobvansale;

public class Productinsertionmodel {



    int id;
    String pname;
    String catogry;
    String qty;
    String srate;
    String prate;
    String edate;

    public static void add(Productinsertionmodel temp) {
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getCatogry() {
        return this.catogry;
    }

    public void setCatogry(String catogry) {
        this.catogry = catogry;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getSrate() { return srate; }

    public void setSrate(String srate) {
        this.srate = srate;
    }

    public String getPrate() {
        return prate;
    }

    public void setPrate(String prate) {
        this.prate = prate;
    }

    public  String getEdate() { return edate; }

    public void setEdate(String edate) {
        this.edate = edate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

