package com.mbg.irg.salaires.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.mbg.irg.salaires.R;

import java.text.DecimalFormat;

public class HomeFragment extends Fragment{
    EditText input , workingDay;
    TextView tv;
    DecimalFormat formatter;
    int Option;
    double Result ,jouvrable,jtravailler;
    String Lan ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Ui Language :
        int ui;

        //from StartPage activity :
        Intent intent = getActivity().getIntent();
        Lan = intent.getStringExtra("lan");


        ui = R.layout.fragment_home_fr ;


        switch (Lan){
            case "fr" :

                ui = R.layout.fragment_home_fr ;

                break;
            case "ar" :
                ui = R.layout.fragment_home_ar ;

                break;

        }

        final View root = inflater.inflate(ui, container, false);


        final LinearLayout option2Layout ;
        option2Layout = root.findViewById(R.id.option3Layout);


        option2Layout.setVisibility(View.GONE);


        //get the spinner from the xml.
        Spinner dropdown0 = root.findViewById(R.id.spinner0);
        //create a list of items for the spinner.
        String[] items0 ;


        items0 = new String[]{"Salarié à temps plein" , "Salarié à temps partiel", "Ayant des besoins spéciaux"};


        switch (Lan){
            case "ar" :

                items0 = new String[]{"مستخدم بدوام كلي" , "مستخدم بدوام جزئي", "متقاعد , ذو احتياجات خاصة"};

                break;
            case "fr" :

                items0 = new String[]{"Salarié à temps plein" , "Salarié à temps partiel", "Ayant des besoins spéciaux"};

                break;

        }

        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, items0);
        //set the spinners adapter to the previously created one.
        dropdown0.setAdapter(adapter);

        dropdown0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0 :
                        //Toast.makeText(getActivity(), "30", Toast.LENGTH_SHORT).show();
                        option2Layout.setVisibility(View.GONE);
                        Option = 1 ;


                        break;

                    case 1 :
                        //Toast.makeText(getActivity(), "26", Toast.LENGTH_SHORT).show();
                        option2Layout.setVisibility(View.VISIBLE);
                        Option = 2;

                        break;

                    case 2 :
                        //Toast.makeText(getActivity(), "22", Toast.LENGTH_SHORT).show();
                        option2Layout.setVisibility(View.GONE);

                        Option = 0 ;
                        break;


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //get the spinner from the xml.
        Spinner dropdown1 = root.findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        String[] items1 ;
        items1 = new String[]{"30 Jours" , "26 Jours", "22 Jours", "173,33 Heurs"};


        switch (Lan){
            case "ar" :

                items1 = new String[]{"30 يوم" , "26 يوم", "22 يوم", "173,33 ساعة"};

                break;
            case "fr" :

                items1 = new String[]{"30 Jours" , "26 Jours", "22 Jours", "173,33 Heurs"};

                break;

        }

        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),R.layout.spinner_item, items1);
        //set the spinners adapter to the previously created one.
        dropdown1.setAdapter(adapter1);


        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0 :
                    //Toast.makeText(getActivity(), "30", Toast.LENGTH_SHORT).show();
                    jouvrable = 30;

                    break;

                    case 1 :
                        //Toast.makeText(getActivity(), "26", Toast.LENGTH_SHORT).show();
                        jouvrable = 26;

                        break;

                    case 2 :
                        //Toast.makeText(getActivity(), "22", Toast.LENGTH_SHORT).show();
                        jouvrable = 22;

                        break;

                    case 3 :
                        //Toast.makeText(getActivity(), "173.33", Toast.LENGTH_SHORT).show();
                        jouvrable = 173.33;

                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        Option = 0;


        formatter = new DecimalFormat("##,###,###");

        tv = root.findViewById(R.id.resultTv);
        input = root.findViewById(R.id.editText);
        workingDay = root.findViewById(R.id.workingd);

        Button calculate;
        ImageButton clearBtn ;
        calculate = root.findViewById(R.id.button);

        clearBtn = root.findViewById(R.id.BtnClear);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            getResult();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                getResult();
            }
        });
        
        
        return root;
    }


    private void getResult()
    {


        if (Option == 0){

            option0();
        }


        if (Option == 1 )
        {

            option1();
        }


        if (Option == 2 )
        {

            option2();
        }


    }




    @SuppressLint("SetTextI18n")
    private void clear()
    {


        switch (Lan){
            case "ar" :

                tv.setText("دج 00");


                break;
            case "fr" :

                tv.setText("DZD 00");


                break;

        }

        input.setText("");
        workingDay.setText("");


    }


    private void option0()
    {

        try {
            //int x = Integer.parseInt(input.getText().toString());
            double x = Double.parseDouble(input.getText().toString());

            if (x <= 30000)
            {

                Result = 0.00;
            }

            if (30000<x && x <=40000)
            {


                Result = (x - 30000) * 0.5 ;
            }

            if (x > 40000)
            {

                Result = ((x-30000)*0.3)+2500 ;
            }

            ShowResult(Result);

        }catch (Exception e)
        {
            wrongInput();
        }

    }

    private void option1()
    {
        try {
            double x = Double.parseDouble(input.getText().toString());

            if (x <= 30000)
            {
                //tv.setText("0.00");
                Result = 0.00;;
            }

            if (30000<x && x <=35000)
            {

                //String y =formatter.format((x - 30000) * 0.8);
                //tv.setText(""+y);
                Result = (x - 30000) * 0.8 ;
            }

            if (x > 35000)
            {
                //String y =formatter.format(((x-30000)*0.3)+2500);
                //tv.setText(""+y);
                Result = ((x-30000)*0.3)+2500 ;
            }

         ShowResult(Result);

        }catch (Exception e)
        {
            wrongInput();
        }

    }



    private void option2() {

        try {

            double x = Integer.parseInt(workingDay.getText().toString());

            double x2 = Double.parseDouble(input.getText().toString());



            double y1 = x2 * (jouvrable/x);

            if (y1 <= 30000)
            {
                //tv.setText("0.00");
                Result = 0.00;;
            }

            if (30000<y1 && y1 <=35000)
            {

                //String y =formatter.format((x - 30000) * 0.8);
                //tv.setText(""+y);
                Result = (y1 - 30000) * 0.8 ;
            }

            if (y1 > 35000)
            {
                //String y =formatter.format(((x-30000)*0.3)+2500);
                //tv.setText(""+y);
                Result = ((y1-30000)*0.3)+2500 ;
            }

            double Result2=  Result * (x/jouvrable) ;

            ShowResult(Result2);


        }catch (Exception e)
        {
            wrongInput();
        }



    }


    private void wrongInput()
    {
        new AlertDialog.Builder(getActivity())
                .setTitle("Erreur :")
                .setMessage("Veuillez vérifier les valeurs saisies !")

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("réessayer", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void ShowResult (double res)
    {
        String rsl = String.format("%.0f", res);

        tv.setText(" دج "+rsl);

        switch (Lan){
            case "ar" :

                tv.setText(" دج "+rsl);


                break;
            case "fr" :

                tv.setText(" DA "+rsl);


                break;

        }



    }


}
