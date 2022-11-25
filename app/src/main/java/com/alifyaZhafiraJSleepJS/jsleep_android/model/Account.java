package com.alifyaZhafiraJSleepJS.jsleep_android.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        /*if(this.email.matches(REGEX_EMAIL) && this.password.matches(REGEX_PASSWORD)){
            return true;
        }
        return false;*/
        Pattern patternEmail = Pattern.compile(REGEX_EMAIL);
        Matcher matcherEmail = patternEmail.matcher(email);
        boolean matchFoundEmail = matcherEmail.find();
        Pattern patternPassword = Pattern.compile(REGEX_PASSWORD);
        Matcher matcherPassword = patternPassword.matcher(password);
        boolean matchFoundPassword = matcherPassword.find();
        if (matchFoundEmail && matchFoundPassword)
            return true;
        else
            return false;
    }

    public String toString () {
        return "Name = " + this.name + "\nEmail = " + this.email + "\nPassword = " + this.password;
    }
    public Object write(){
        return null;
    }
    public boolean read(String x){
        return false;
    }
}