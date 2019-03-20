package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Modele  {

        int id ;
        public static float somme ;
        private String produit ;
        private Float prix ;
        String date ;
    public Modele () {}
    public Modele(String produit, Float prix) {
            this.produit = produit;
            this.prix = prix;
            String pattern = "MM/dd/yyyy HH:mm:ss";

            DateFormat df = new SimpleDateFormat(pattern);
            Date today = Calendar.getInstance().getTime();

            date = df.format(today);

        }

        public String getProduit() {
            return produit;
        }

        public Float getPrix() {
            return prix;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setProduit(String produit) {
            this.produit = produit;
        }

        public void setPrix(Float prix) {
            this.prix = prix;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }
    }

