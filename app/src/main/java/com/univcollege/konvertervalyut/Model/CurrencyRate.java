package com.univcollege.konvertervalyut.Model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Aman on 24/03/2018.
 */

public class CurrencyRate {

    double[] rateList= {23.01, 6.8985, 1, 0.9209, 113.335, 0.7774, 57.157};

    public float loadShared(Context context, String s){
        SharedPreferences mSharedPreferences= context.getSharedPreferences("currency", Context.MODE_PRIVATE);
        Float currency= mSharedPreferences.getFloat(s, 10);
        return  currency;
    }

    public double Currency(Context context, double rate, String fromCurrency)
    {
        //rating according to dollar
        if (fromCurrency.equals("Birr")) {
            if (loadShared(context,"Birr")!=10){
                rate= loadShared(context,"Birr");
            }else {
                rate = rateList[0];
            }
        }
        else if (fromCurrency.equals("Chinese Yuan"))
            if (loadShared(context,"Chinese Yuan")!=10){
                rate=loadShared(context,"Chinese Yuan");
            }else {
                rate = rateList[1];;
            }
        else if (fromCurrency.equals("Dollar"))
            rate=1;
        else if (fromCurrency.equals("Euro"))
            if (loadShared(context,"Euro")!=10){
                rate= loadShared(context,"Euro");
            }else {
                rate = rateList[3];;
            }
        else if (fromCurrency.equals("Japanese Yen"))
            if (loadShared(context,"Japanese Yen")!=10) {
                rate= loadShared(context,"Japanese Yen");
            }else{
                rate= rateList[4];;
            }
        else if (fromCurrency.equals("Pound"))
            if (loadShared(context,"Pound")!=10){
                rate=loadShared(context,"Pound");
            }else {
                rate = rateList[5];;
            }
        else if (fromCurrency.equals("Ruble"))
            if (loadShared(context,"Ruble")!=10){
                rate=loadShared(context,"Ruble");
            }else {
                rate = rateList[6];;
            }

        return  rate;
    }

}
