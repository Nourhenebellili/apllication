package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ListView liste ;

    public static DBadapter db ;
    public static adapter array ;
    public static ArrayList<Modele> base ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= new DBadapter(this);
        base=new ArrayList<Modele>();
        base= db.afficher();
        liste=findViewById(R.id.liste);
        array=new adapter(this,R.layout.activity_modele,base);

        liste.setAdapter(array);

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id){
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Supprimer achat")
                        .setMessage("Voulez-vous supprimer cet achat?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                db.remove(base.get(position).getId());
                                base=db.afficher();

                                array=new adapter(MainActivity.this,R.layout.activity_modele,base);

                                liste.setAdapter(array);
                                Toast.makeText(MainActivity.this, "supprimé", Toast.LENGTH_SHORT).show(); }})
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }

        }     );


    }

    @Override
    public void onResume(){
        super.onResume();

        base=db.afficher();
        array=new adapter(this,R.layout.activity_modele,base);

        liste.setAdapter(array);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_options,menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if( item.getItemId()==R.id.ajouter)
        {
            Intent intent = new Intent(MainActivity.this, Ajouter.class); startActivity(intent);
        }

        if( item.getItemId()==R.id.total)
        {
            Intent intent = new Intent(MainActivity.this, Page.class); startActivity(intent);
        }
        return true ;
    }

    public static long getAppFirstInstallTime(Context context){
        PackageInfo packageInfo;
        try {
            if(Build.VERSION.SDK_INT>8/*Build.VERSION_CODES.FROYO*/ ){
                packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                return packageInfo.firstInstallTime;
            }else{

                ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
                String sAppFile = appInfo.sourceDir;
                return new File(sAppFile).lastModified();
            }
        } catch (PackageManager.NameNotFoundException e) {

            return 0;
        }
    }
    public static String getDate(long milliSeconds, String dateFormat) {

        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

}
