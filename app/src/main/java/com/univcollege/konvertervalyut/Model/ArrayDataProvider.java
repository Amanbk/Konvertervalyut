package com.univcollege.konvertervalyut.Model;

/**
 * Created by Aman on 5/13/2017.
 */

public class ArrayDataProvider {
    private int image;
    private String currency;
    private String rate;



    public ArrayDataProvider(int image, String currency, String rate) {
        this.image = image;
        this.currency = currency;
        this.rate = rate;
    }

    public int getImage() {
        return image;
    }

    public String getCurrency() {
        return currency;
    }

    public String getRate() { return rate; }

}
