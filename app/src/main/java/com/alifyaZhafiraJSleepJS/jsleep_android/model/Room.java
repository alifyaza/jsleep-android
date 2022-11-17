package com.alifyaZhafiraJSleepJS.jsleep_android.model;
import com.alifyaZhafiraJSleepJS.jsleep_android.model.BedType;
import com.alifyaZhafiraJSleepJS.jsleep_android.model.City;
import com.alifyaZhafiraJSleepJS.jsleep_android.model.Facility;
import com.alifyaZhafiraJSleepJS.jsleep_android.model.Price;
import com.alifyaZhafiraJSleepJS.jsleep_android.model.Serializable;

import java.util.*;
import java.util.ArrayList;

public class Room extends Serializable
{
    public int size;
    public String name;
    public Facility facility;
    public Price price;
    public String address;
    public BedType bedType;
    public City city;
    public ArrayList<Date> booked;
    public int accountId;

    public Room(int accountId, String name, int size, Price price, Facility facility, City city, String address) {
        //super();
        this.accountId = accountId;
        this.name = name;
        this.size = size;
        this.price = price;
        this.address = address;
        this.city = city;
        this.facility = facility;
    }
    /*public Room(int id, String name, int size, Price price, Facility facility, City city, String address) {
        //super(id);
        this.name = name;
        this.size = size;
        this.price = price;
        this.address = address;
        this.bedType = bedType;
        this.city = city;
        this.facility = facility;
        this.bedType = BedType.SINGLE;

        //ArrayList<Date> booked = new ArrayList<Date>();
    }*/

    public String toString () {
        return "Name = " + name + "\nSize = " + size + "\nPrice = " + price +
        "\nFacility = " + facility + "\nBed Type = " + bedType + "\nCity = " +
        city + "\nAddress = " + address;
    }

    public Object write(){
        return null;
    }
    
    public boolean read(String x){
        return true;
    }
}
