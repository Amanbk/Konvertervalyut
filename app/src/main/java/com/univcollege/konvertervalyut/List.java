package com.univcollege.konvertervalyut;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.univcollege.konvertervalyut.Model.ArrayDataProvider;


/**
 * A simple {@link Fragment} subclass.
 */
public class List extends Fragment {

    private ListView mListView;
    private Spinner currencyMany;
    private TextView mRate, mRelative, mRelativeTo;
    private TextView mRate2;
    int[] flags ={R.drawable.ethiopia, R.drawable.china, R.drawable.usa, R.drawable.euro, R.drawable.japan, R.drawable.britain,  R.drawable.russia};

    String[] currency;
    String[] rate= new String[7];
    ListAdapter adapter;
    double[] rate5= {23.01, 6.8985, 1, 0.9209, 113.335, 0.7774, 57.157};

    double rate2;

    public List() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View myFrangmentView=inflater.inflate(R.layout.fragment_list, container, false);

        mListView= (ListView) myFrangmentView.findViewById(R.id.listView);
        mRate2= (TextView) myFrangmentView.findViewById(R.id.rateList);
        currencyMany= (Spinner) myFrangmentView.findViewById(R.id.currencyMany);
        currency= getResources().getStringArray(R.array.currency);
        mRelativeTo= (TextView) myFrangmentView.findViewById(R.id.relativeTo);

        adapter= new ListAdapter(getContext(), R.layout.row_layout);
        mListView.setAdapter(adapter);
        for (int i=0;i<flags.length;i++){
            if (loadShared(currency[i])!=10) {
                rate5[i] = loadShared(currency[i]);
            }
        }
        for (int i=0;i<flags.length;i++){
            ArrayDataProvider arrayDataProvider= new ArrayDataProvider(flags[i],currency[i],String.valueOf(rate5[i]));
            adapter.add(arrayDataProvider);
        }

        mRelativeTo.setText("Относительно");


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (!String.valueOf(currencyMany.getSelectedItem()).equals("Dollar")) {
                    Toast.makeText(getContext(), "To Change Currency Value, select Relative to Dollar", Toast.LENGTH_LONG).show();

                }

                else{

                    AlertDialog.Builder alertDialog= new AlertDialog.Builder(getContext());
                    alertDialog.setTitle(currency[position]);


                    alertDialog.setMessage("Type new Exchange Rate");
                    alertDialog.setIcon(flags[position]);

                    final EditText input= new EditText(getContext());
                    input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL| InputType.TYPE_NUMBER_FLAG_SIGNED);

                    alertDialog.setView(input);

                    alertDialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alertDialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int currPosition=position;
                            String sInput=  String.valueOf(input.getText());
                            double dInput= Double.parseDouble(sInput);
                            try {
                                saveShared(currency[currPosition], dInput);
                            }catch(Exception e){
                                Toast.makeText(getContext(),"error",Toast.LENGTH_SHORT).show();
                            }
                            recreat();
                            Toast.makeText(getContext(),currency[currPosition] +" changed", Toast.LENGTH_SHORT).show();

                        }
                    });
                    alertDialog.show();
                    //Toast.makeText(getContext(), currency[position],Toast.LENGTH_SHORT).show();
                }


            }

        });
        currencyMany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                adapter= new ListAdapter(getContext(), R.layout.row_layout);
                mListView.setAdapter(adapter);
                if (String.valueOf(currencyMany.getSelectedItem()).equals("Birr")) {
                    convertCurrency();
                }else if (String.valueOf(currencyMany.getSelectedItem()).equals("Chinese Yuan")) {
                    convertCurrency();
                } else if (String.valueOf(currencyMany.getSelectedItem()).equals("Dollar")) {
                    convertCurrency();
                } else if (String.valueOf(currencyMany.getSelectedItem()).equals("Euro")) {
                    convertCurrency();
                } else if (String.valueOf(currencyMany.getSelectedItem()).equals("Japanese Yen")) {
                    convertCurrency();
                } else if (String.valueOf(currencyMany.getSelectedItem()).equals("Pound")) {
                    convertCurrency();
                } else if (String.valueOf(currencyMany.getSelectedItem()).equals("Ruble")) {
                    convertCurrency();
                }
                Log.d("aa",rate[3]);

                for (int i=0;i<flags.length;i++) {
                    ArrayDataProvider arrayDataProvider = new ArrayDataProvider(flags[i], currency[i], rate[i]);
                    adapter.add(arrayDataProvider);
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return myFrangmentView;
    }
    public double Currency()
    {
        String sCurrencyMany= String.valueOf(currencyMany.getSelectedItem());
        //rating according to dollar

        if (sCurrencyMany.equals("Birr"))
            rate2=rate5[0];
        else if (sCurrencyMany.equals("Chinese Yuan"))
            rate2=rate5[1];
        else if (sCurrencyMany.equals("Dollar"))
            rate2=rate5[2];
        else if (sCurrencyMany.equals("Euro"))
            rate2=rate5[3];
        else if (sCurrencyMany.equals("Japanese Yen"))
            rate2=rate5[4];
        else if (sCurrencyMany.equals("Pound"))
            rate2=rate5[5];
        else if (sCurrencyMany.equals("Ruble"))
            rate2=rate5[6];

        return  1/rate2;
    }
    public void convertCurrency(){
        double rate3, rate4;
        for (int i=0; i<=6; i++){
            rate3= Math.round(Currency()* rate5[i]*10000);
            rate3/=10000;
            rate4=rate3;
            rate[i] = String.valueOf(rate4);
        }
    }
    public void saveShared(String s, double a){
        SharedPreferences mSharedPreferences= this.getActivity().getSharedPreferences("currency", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= mSharedPreferences.edit();
        editor.putFloat(s, (float) a);
        editor.commit();
    }
    public float loadShared(String s){
        SharedPreferences mSharedPreferences= this.getActivity().getSharedPreferences("currency", Context.MODE_PRIVATE);
        Float currency= mSharedPreferences.getFloat(s, 10);
        return  currency;
    }
    public void recreat(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();

    }

}
