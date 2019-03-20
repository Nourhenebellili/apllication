package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import static com.example.myapplication.MainActivity.db;

public class Ajouter extends AppCompatActivity {
    Button confirmerj ;
    EditText produitj ;
    EditText prix ;
    ListView liste ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);
        produitj=findViewById(R.id.produit);
        prix=findViewById(R.id.prix);
        confirmerj=findViewById(R.id.confirmer);


        confirmerj.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v)
            {
                String achats = produitj.getText().toString();
                Float prixx =Float.valueOf(prix.getText().toString());
                Modele nouveau = new Modele(achats, prixx);

                db.ajoutmodele(nouveau);
                Toast.makeText(Ajouter.this, "Le produit est ajouté avec succés ", Toast.LENGTH_SHORT).show();

            }

        });
    }
}
