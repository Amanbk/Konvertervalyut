package com.univcollege.konvertervalyut;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.univcollege.konvertervalyut.Model.CurrencyRate;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    private Spinner mFromCurrency, mToCurrency;
    private EditText mValue;
    private TextView mConvertedValue, mRate;

    private String fromCurrency, toCurrency, fromSign;
    private double rate, value;
    private Switch mSwitch;

    private Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0, btdot, btbackspace;


    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View MyFragmentView = inflater.inflate(R.layout.fragment_home, container, false);


        mFromCurrency= (Spinner) MyFragmentView.findViewById(R.id.fromCurrency);
        mToCurrency= (Spinner) MyFragmentView.findViewById(R.id.toCurrency);
        mValue= (EditText) MyFragmentView.findViewById(R.id.value);
        mConvertedValue= (TextView) MyFragmentView.findViewById(R.id.convertedValue);
        mRate = (TextView) MyFragmentView.findViewById(R.id.rate);
        mSwitch= (Switch) MyFragmentView.findViewById(R.id.switch1);

        mSwitch.setChecked(true);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!mSwitch.isChecked())
                    mRate.setText("");
                else{
                    mRate.setText("1 " + fromRateString() +" = "+ convert()+" "+toRateString());
                }
            }
        });
        mValue.setKeyListener(null);
        mFromCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mSwitch.isChecked())
                    mRate.setText("1 " + fromRateString() +" = "+ convert()+" "+toRateString());
                else
                    mRate.setText("");
                if (value>0.01)
                    mConvertedValue.setText(convert(value) + "");
                else
                    mConvertedValue.setText("");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mToCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mSwitch.isChecked())
                    mRate.setText("1" + fromRateString() +" = "+ convert()+" " +toRateString());
                else
                    mRate.setText("");
                if (value>0.01)
                    mConvertedValue.setText(convert(value) + "");
                else
                    mConvertedValue.setText("");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    value = Double.parseDouble(String.valueOf(mValue.getText()));
                    mConvertedValue.setText(convert(value) + "");
                }catch(Exception e){
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        bt0= (Button) MyFragmentView.findViewById(R.id.bt0);
        bt1= (Button) MyFragmentView.findViewById(R.id.bt1);
        bt2= (Button) MyFragmentView.findViewById(R.id.bt2);
        bt3= (Button) MyFragmentView.findViewById(R.id.bt3);
        bt4= (Button) MyFragmentView.findViewById(R.id.bt4);
        bt5= (Button) MyFragmentView.findViewById(R.id.bt5);
        bt6= (Button) MyFragmentView.findViewById(R.id.bt6);
        bt7= (Button) MyFragmentView.findViewById(R.id.bt7);
        bt8= (Button) MyFragmentView.findViewById(R.id.bt8);
        bt9= (Button) MyFragmentView.findViewById(R.id.bt9);
        btdot= (Button) MyFragmentView.findViewById(R.id.btdot);
        btbackspace= (Button) MyFragmentView.findViewById(R.id.btbackspace);

        bt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValue.append("0");
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValue.append("1");
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValue.append("2");
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValue.append("3");
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValue.append("4");
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValue.append("5");
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValue.append("6");
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValue.append("7");
            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValue.append("8");
            }
        });
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValue.append("9");
            }
        });
        btdot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValue.append(".");
            }
        });
        btbackspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValue.setText(""); mConvertedValue.setText("");
            }
        });

        return MyFragmentView;
    }
    public double convert(){
        double convertedValue= toRate()/fromRate();
        convertedValue*=1000;
        double roundConverterValue = Math.round(convertedValue);
        roundConverterValue/=1000;
        return roundConverterValue;
    }
    public double convert(double value) {
        double convertedValue = (value * toRate()) / fromRate();
        CurrencyRate mCurrencyRate= new CurrencyRate();
        double roundConverterValue = 1;
        if (mCurrencyRate.loadShared(getContext(),"round") == 1) {

            if (mCurrencyRate.loadShared(getContext(),"rounding") != 10) {
                int num2 = 0;
                if (mCurrencyRate.loadShared(getContext(),"rounding") == 1937)
                    num2 = 0;
                else if (mCurrencyRate.loadShared(getContext(),"rounding") == 1940)
                    num2 = -1;
                else if (mCurrencyRate.loadShared(getContext(),"rounding") == 1900)
                    num2 = -2;
                convertedValue *= Math.pow(10, num2);
                roundConverterValue = Math.round(convertedValue);
                roundConverterValue /= Math.pow(10, num2);
            }else {
                    convertedValue *= 0.1;
                    roundConverterValue = Math.round(convertedValue);
                    roundConverterValue /= 0.1;
                }

        } else {
            if (mCurrencyRate.loadShared(getContext(),"decimal") != 10) {
                int num = 0;
                if (mCurrencyRate.loadShared(getContext(),"decimal") < 0.01f)
                    num = 0;
                else if (mCurrencyRate.loadShared(getContext(),"decimal") < 0.101f)
                    num = 1;
                else if (mCurrencyRate.loadShared(getContext(),"decimal") < 0.1101f)
                    num = 2;
                else if (mCurrencyRate.loadShared(getContext(),"decimal") < 0.11101f)
                    num = 3;
                convertedValue *= Math.pow(10, num);
                roundConverterValue = Math.round(convertedValue);
                roundConverterValue /= Math.pow(10, num);
            } else {
                convertedValue *= 100;
                roundConverterValue = Math.round(convertedValue);
                roundConverterValue /= 100;
            }
        }


        return roundConverterValue;

    }

    public double fromRate() {
        fromCurrency = String.valueOf(mFromCurrency.getSelectedItem());
        CurrencyRate mCurrencyRate= new CurrencyRate();
        return mCurrencyRate.Currency(getContext(), rate,fromCurrency);
    }
    public double toRate(){
        toCurrency = String.valueOf(mToCurrency.getSelectedItem());
        CurrencyRate mCurrencyRate= new CurrencyRate();
        return mCurrencyRate.Currency(getContext(), rate,fromCurrency);
    }

    public String fromRateString(){
        fromCurrency = String.valueOf(mFromCurrency.getSelectedItem());
        return CurrencySign(fromCurrency);
    }
    public String toRateString(){
        toCurrency = String.valueOf(mToCurrency.getSelectedItem());
        return CurrencySign(toCurrency);
    }
    public String CurrencySign(String fromCurrency){
        //rating according to dollar
        if (fromCurrency.equals("Birr"))
            fromSign= "ETB";
        else if (fromCurrency.equals("Chinese Yuan"))
            fromSign= "¥";
        else if (fromCurrency.equals("Dollar"))
            fromSign= "$";
        else if (fromCurrency.equals("Euro"))
            fromSign="€";
        else if (fromCurrency.equals("Japanese Yen"))
            fromSign= "yen";
        else if (fromCurrency.equals("Pound"))
            fromSign="£";
        else if (fromCurrency.equals("Ruble"))
            fromSign="RUB";
        return  fromSign;
    }

}
