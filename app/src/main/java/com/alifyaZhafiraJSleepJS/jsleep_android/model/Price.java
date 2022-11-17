package com.alifyaZhafiraJSleepJS.jsleep_android.model;

public class Price
{
    public double price;
    public double discount;
    
    public Price (double price) {
        this.price = price;
    }
    
    public Price (double price, double discount) {
        this.price = price;
        this.discount = discount;
    }
    
    public String toString() {
        return "Price = " + this.price + "\nDiscount = " + this.discount;
    }
}
    /*
    public double rebate;
    public double price;
    public int discount;
    
    public Price (double price) {
        this.price = price;
        rebate = 0;
        discount = 0;
    }

    public Price (double price, int discount) {
        this.price = price;
        this.discount = discount;
        rebate = 0;
    }
    
    public Price (double price, double rebate) {
        this.price = price;
        this.rebate = rebate;
        discount = 0;
    }
    
    private double getDiscountedPrice() {
        
        if (discount > 100.0){
            return 100.0;
        } else if (discount == 100.0) {
            return 0;
        } else {
            return price - (price * discount)/100;
        }
    }
    
    private double getRebatedPrice() {
        if (rebate > price) {
            return rebate = price;
        }
        
        return price - rebate; 
    }
    
}
*/
