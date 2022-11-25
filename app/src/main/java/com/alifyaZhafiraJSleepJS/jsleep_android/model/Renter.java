package com.alifyaZhafiraJSleepJS.jsleep_android.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Renter extends Serializable
{
    public String phoneNumber;
    public String address;
    public String username;

    public final static String REGEX_NAME = "^[A-Z][A-Za-z0-9_]{4,20}$";
    public final static String REGEX_PHONE = "^[0-9]{9,12}$";

    public Renter(String username, String phoneNumber, String address) {
        //super();
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public boolean validate() {
        /*if(this.username.matches(REGEX_NAME) && this.phoneNumber.matches(REGEX_PHONE)){
            return true;
        }
        return false;*/
        Pattern patternName = Pattern.compile(this.REGEX_NAME);
        Matcher nameMatcher = patternName.matcher(this.username);
        boolean matchFoundName = nameMatcher.find();
        Pattern patternPhone = Pattern.compile(this.REGEX_PHONE);
        Matcher phoneMatcher = patternPhone.matcher(this.phoneNumber);
        boolean matchFoundNum = phoneMatcher.find();

        if (matchFoundNum && matchFoundName)
            return true;
        else
            return false;
    }
    /*public Renter(int id, String username)
    {
        super();
        this.username = username;
    }

    public Renter(int id, String username, String address) {
        super();
        this.username = username;
        this.address = address;
    }

    public Renter(int id, String username, int phoneNumber) {
        super();
        this.username = username;
        this.phoneNumber = phoneNumber;
    }

    public Renter(int id, String username, int phoneNumber, String address) {
        super();
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }*/

}
