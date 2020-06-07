package com.mbg.irg.salaires;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class startPage extends AppCompatActivity {
    public  static  ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);


        ImageView logo = findViewById(R.id.logoImg);
        Animation expandIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.expand_in);
        logo.startAnimation(expandIn);

        //spinner

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        String[] items = new String[]{"","AR", "FR"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);


        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {


                switch ((String) parent.getItemAtPosition(position)){
                    case "FR" :
                        ((TextView)view).setText(null);

                        moreInfoFr();
                        break;

                    case "AR" :
                        ((TextView)view).setText(null);

                        moreInfoAr();
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });




    }



    public void moreInfoAr() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (!isFinishing()){
                    new AlertDialog.Builder(startPage.this)
                            .setTitle("info")
                            .setMessage("وصف التطبيق هنا ..... ")
                            .setCancelable(true)
                            .setPositiveButton("تقييم", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Uri uri = Uri.parse("market://details?id=" + startPage.this.getPackageName());
                                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                                    // To count with Play market backstack, After pressing back button,
                                    // to taken back to our application, we need to add following flags to intent.
                                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                                    try {
                                        startActivity(goToMarket);
                                    } catch (ActivityNotFoundException e) {
                                        startActivity(new Intent(Intent.ACTION_VIEW,
                                                Uri.parse("http://play.google.com/store/apps/details?id=" + startPage.this.getPackageName())));
                                    }
                                }
                            })
                            .setNegativeButton("شارك", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    shareFunction();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .show();
                }
            }
        });


    }

    public void moreInfoFr() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (!isFinishing()){
                    new AlertDialog.Builder(startPage.this)
                            .setTitle("info")
                            .setMessage("info EXMPLE ......")
                            .setCancelable(true)
                            .setPositiveButton("évaluer", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Uri uri = Uri.parse("market://details?id=" + startPage.this.getPackageName());
                                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                                    // To count with Play market backstack, After pressing back button,
                                    // to taken back to our application, we need to add following flags to intent.
                                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                                    try {
                                        startActivity(goToMarket);
                                    } catch (ActivityNotFoundException e) {
                                        startActivity(new Intent(Intent.ACTION_VIEW,
                                                Uri.parse("http://play.google.com/store/apps/details?id=" + startPage.this.getPackageName())));
                                    }
                                }
                            })

                            .setNegativeButton("partager", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    shareFunction();                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .show();
                }
            }
        });


    }

    public void arabic(View view) {
        Intent myIntent2 = new Intent(startPage.this, MainActivity.class);
        myIntent2.putExtra("lan", "ar");
        startPage.this.startActivity(myIntent2);
        dialog = ProgressDialog.show(startPage.this, "",
                "جار التحميل. الرجاء الإنتظار...", true);
        //this.finish();


    }

    public void france(View view) {
        Intent myIntent2 = new Intent(startPage.this, MainActivity.class);
        myIntent2.putExtra("lan", "fr");
        startPage.this.startActivity(myIntent2);
        dialog = ProgressDialog.show(startPage.this, "",
                "chargement en cours. veuillez patienter ...", true);
        //this.finish();

    }

    public void share(View view) {


        shareFunction();

    }

    public void shareFunction()
    {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Barème IRG Salaires 2021");
            String shareMessage= "\nJe vous invite à télécharger l'application : Barème IRG Salaires 2021\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choisissez-en un"));
        } catch(Exception e) {
            //e.toString();



        }

    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setMessage("Voulez-vous vraiment fermer l'application?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("Non", null)
                .show();
    }
}



