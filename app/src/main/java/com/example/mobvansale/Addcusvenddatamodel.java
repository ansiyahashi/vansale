package com.example.mobvansale;

public class Addcusvenddatamodel {
    int id;
    String cvname;
    String shname;
    String  address;
    String email;
    String phno;
    String creditbalence="0";
    String rid;
    String status="0";
    String discount;
    
    
    public String getcvname() {
        return cvname;
    }

    public String getshname() {
        return shname;
    }

    public String getaddress() {
        return address;
    }


    public String getphno() {
        return phno;
    }

    public String getemail() {
        return email;
    }

    public String getcreditbalance() {
        return creditbalence;
    }

    public String getrid() {
        return rid;
    }


    public String getstatus() {
        return status;
    }

    public String getdiscount() {
        return discount;
    }

    public void setcvname(String cvname) {this.cvname=cvname;

    }

    public void setShname(String shname) {this.shname=shname;
    }

    public void setAddress(String address) {this.address=address;
    }

    public void setEmail(String email) {this.email=email;
    }

    public void setPhno(String phno) {this.phno=phno;
    }

    public void setDiscount(String discount) {this.discount=discount;
    }

    public void setRoute(String rid) {this.rid=rid;
    }
}
