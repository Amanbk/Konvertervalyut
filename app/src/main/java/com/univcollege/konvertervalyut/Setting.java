package com.univcollege.konvertervalyut;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Setting extends Fragment {

    private Button resetChanges;

    private CheckBox  settingCheckBox2;
    private Spinner settingSpinner, settingSpinner2;
    public Setting() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View Setting= inflater.inflate(R.layout.fragment_setting, container, false);
        settingSpinner= (Spinner) Setting.findViewById(R.id.settingSpinner);
        settingSpinner2= (Spinner) Setting.findViewById(R.id.settingSpinner2);

        settingCheckBox2= (CheckBox) Setting.findViewById(R.id.settingCheckBox2);

        settingCheckBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (settingCheckBox2.isChecked()){
                    saveShared("round",1);
                }else{
                    saveShared("round",0);
                }
                recreat();
            }
        });
        if (loadShared("round")==1){
            settingCheckBox2.setChecked(true);
            settingSpinner.setEnabled(false);
            settingSpinner2.setEnabled(true);
        }else{
            settingSpinner.setEnabled(true);
            settingSpinner2.setEnabled(false);
        }
        if (loadShared("rounding")!=10){
            int num=0;
            if (loadShared("rounding")==1937)
                num=0;
            else if (loadShared("rounding")==1940)
                num=1;
            else if (loadShared("rounding")==1900)
                num=2;
            settingSpinner2.setSelection(num);
        }else{
            settingSpinner2.setSelection(1);
        }

        if (!settingCheckBox2.isChecked()) {
            if (loadShared("decimal") != 10) {
                int num = 0;
                if (loadShared("decimal") < 0.01f)
                    num = 0;
                else if (loadShared("decimal") < 0.101f)
                    num = 1;
                else if (loadShared("decimal") < 0.1101f)
                    num = 2;
                else if (loadShared("decimal") < 0.11101f)
                    num = 3;
                settingSpinner.getItemAtPosition(num);
            } else {
                settingSpinner.getItemAtPosition(1);
            }
        }

        if (loadShared("rounding") != 10) {
            int num2 = 0;
            if (loadShared("rounding") == 1937)
                num2 = 0;
            else if (loadShared("rounding") == 1940)
                num2 = 1;
            else if (loadShared("rounding") == 1900)
                num2 = 2;
            settingSpinner2.getItemAtPosition(num2);
        }else{
            settingSpinner2.getItemAtPosition(1);
        }
        settingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerDecimal= String.valueOf(settingSpinner.getSelectedItem());
                double dSpinnerDecimal= Double.parseDouble(spinnerDecimal);
 //               Toast.makeText(getContext(), "Decimal Selected", Toast.LENGTH_SHORT).show();
                saveShared("decimal", dSpinnerDecimal);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        settingSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerDecimal= String.valueOf(settingSpinner2.getSelectedItem());
                double dSpinnerDecimal= Double.parseDouble(spinnerDecimal);
              //  Toast.makeText(getContext(), "Rounding Selected", Toast.LENGTH_SHORT).show();
                saveShared("rounding", dSpinnerDecimal);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        resetChanges= (Button) Setting.findViewById(R.id.resetChanges);
        resetChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteShared();
                settingCheckBox2.setChecked(false);
                Toast.makeText(getContext(), "All changes has been reset to Default. ",Toast.LENGTH_LONG).show();
                recreat();
                restartApp();

            }
        });


        return Setting;
    }
    public void deleteShared(){
        SharedPreferences sharedPreferences= this.getActivity().getSharedPreferences("currency", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }
    public float loadShared(String s){
        SharedPreferences mSharedPreferences= this.getActivity().getSharedPreferences("currency", Context.MODE_PRIVATE);
        Float currency= mSharedPreferences.getFloat(s, 10);
        return  currency;
    }
    public void saveShared(String s, double a){
        SharedPreferences mSharedPreferences= this.getActivity().getSharedPreferences("currency", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= mSharedPreferences.edit();
        editor.putFloat(s, (float) a);
        editor.commit();
    }
    public void recreat(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();

    }
    public void restartApp(){
        Intent i= getActivity().getBaseContext().getPackageManager().getLaunchIntentForPackage(
                getActivity().getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

    }

}
