package com.alifyaZhafiraJSleepJS.jsleep_android.model;

import com.alifyaZhafiraJSleepJS.jsleep_android.model.*;

public class Account extends Serializable {

    public String name;
    public String email;
    public String password;

    public final static String REGEX_EMAIL = "^[A-Za-z0-9]+@(.+)$";
    public final static String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
    public Renter renter;
    public double balance;

    public Account (String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = 0;
    }

    public boolean validate() {
        if(this.email.matches(REGEX_EMAIL) && this.password.matches(REGEX_PASSWORD)){
            return true;
        }
        return false;
    }

    public String toString () {
        return "Account{" +
                "balance=" + balance +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password'" + password + '\'' +
                ", renter=" + renter +
                '}';
    }

    public Object write(){
        return null;
    }
    public boolean read(String x){
        return false;
    }
}