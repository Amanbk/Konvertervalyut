package com.univcollege.konvertervalyut;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.univcollege.konvertervalyut.Model.ArrayDataProvider;

import java.util.ArrayList;

/**
 * Created by Aman on 5/13/2017.
 */

public class ListAdapter extends ArrayAdapter {
    ArrayList list= new ArrayList();
    public ListAdapter(Context context, int resource) {
        super(context, resource);
    }
    static class DataHandler
    {
        ImageView flag;
        TextView currency;
        TextView rate;
    }
    public void add(Object object)
    {
        super.add(object);
        list.add(object);
    }
    public int getCount(){
        return this.list.size();
    }

    public Object getItem(int position){
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row=convertView;
        DataHandler handler;
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_layout,parent,false);
            handler= new DataHandler();

            handler.flag= (ImageView) row.findViewById(R.id.imageFlag);
            handler.currency=(TextView) row.findViewById(R.id.currency);
            handler.rate= (TextView) row.findViewById(R.id.rateList);

            row.setTag(handler);
        }else{
            handler= (DataHandler)row.getTag();

        }
        ArrayDataProvider arrayDataProvider;
        arrayDataProvider=(ArrayDataProvider)this.getItem(position);
        handler.flag.setImageResource(arrayDataProvider.getImage());
        handler.currency.setText(arrayDataProvider.getCurrency());
        handler.rate.setText(arrayDataProvider.getRate());

        return row;
    }
}
