package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.myapplication.MainActivity.db;
import static com.example.myapplication.MainActivity.getDate;

public class Page extends AppCompatActivity {
    Long date ;
    TextView totalj,affichagedate ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        totalj=findViewById(R.id.total);
        totalj.setText(Float.toString(db.total()));
        //date= getAppFirstInstallTime(this);
        String d = getDate(date, "dd/MM/yyyy");
        affichagedate=findViewById(R.id.date);
        affichagedate.setText(d);


    }

    @Override
    public void onResume(){
        super.onResume();
        totalj.setText(Float.toString(db.total()));
    }
    }
