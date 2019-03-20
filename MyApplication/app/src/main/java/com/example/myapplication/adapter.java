package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter extends ArrayAdapter<Modele> {
    private ArrayList<Modele> arrayList;
    private Context ctx;
    private int item;


    public adapter(@NonNull Context context, int resource , ArrayList<Modele> myarray) {
        super(context, resource,myarray);
        this.arrayList=myarray;
        this.ctx=context;
        this.item=resource; }

    @NonNull
    @Override
    public boolean isEnabled(int position)
    {
        return true;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //super.getView(position, convertView, parent);
        convertView= LayoutInflater.from(ctx).inflate(item,parent,false);

        TextView prix=convertView.findViewById(R.id.prixx);

        TextView  achat=convertView.findViewById(R.id.objet);

        TextView date=convertView.findViewById(R.id.datex);

        prix.setText(Float.toString(arrayList.get(position).getPrix()));
        achat.setText(arrayList.get(position).getProduit());
        date.setText(arrayList.get(position).getDate());

        return convertView; }}