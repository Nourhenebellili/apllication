package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBadapter extends SQLiteOpenHelper {
    public DBadapter(Context context)
    {
        super(context,"myapplication",null,1);
    }

    public void onCreate(SQLiteDatabase db) {
        String creatTable="create table modele(id integer primary key,produit text ,prix float, date text)";
        db.execSQL(creatTable); }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deleteTable=("drop table if exists depense ");
        db.execSQL(deleteTable);
        onCreate(db); }

    public void ajoutmodele(Modele nouveau){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("produit",nouveau.getProduit());
        contentValues.put("prix",nouveau.getPrix());
        contentValues.put("date",nouveau.getDate());
        db.insert("modele",null,contentValues);

    }

    public ArrayList<Modele> afficher(){
        SQLiteDatabase db=getReadableDatabase();
        String selectall="SELECT * FROM depense";
        Cursor cursor= db.rawQuery(selectall,null);
        ArrayList<Modele> depenses=new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
                Modele nouveau=new Modele(cursor.getString(1),cursor.getFloat(2));
                nouveau.setId(cursor.getInt(0));
                nouveau.setDate(cursor.getString(3));
                depenses.add(nouveau); }
            while(cursor.moveToNext()); }
        return depenses;}

    public void remove(int id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete("depense","id="+Integer.toString(id),null);}

    public Float total()
    {   Float s =0f ;

        SQLiteDatabase db=getReadableDatabase();
        String selectall="SELECT * FROM depense";
        Cursor cursor= db.rawQuery(selectall,null);
        ArrayList<Modele> depenses=new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
                s+=cursor.getFloat(2);
            }
            while(cursor.moveToNext());
        }

        return s ;}

}
