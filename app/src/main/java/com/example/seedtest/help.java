package com.example.seedtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class help extends AppCompatActivity {
    ArrayList<String> arrayList1,arrayList2,arrayList3;
    ArrayAdapter<String> arrayadapter_List1,arrayadapter_List2,arrayadapter_List3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Spinner spinner= (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);

        arrayList1=new ArrayList<>();
        arrayList1.add("Report a Problem");
        arrayList1.add("Give Suggestions");
        arrayList1.add("FeedBack");
        arrayadapter_List1= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayList1);
        spinner.setAdapter(arrayadapter_List1);

        arrayList2=new ArrayList<>();
        arrayList2.add("Is app working on android? Yes");
        arrayList2.add("Is it Free? Yes");
        arrayadapter_List2= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayList2);
        spinner2.setAdapter(arrayadapter_List2);


        arrayList3=new ArrayList<>();
        arrayList3.add("191156@students.au.edu.pk");
        arrayList3.add("191162@students.au.edu.pk");
        arrayadapter_List3= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayList3);
        spinner3.setAdapter(arrayadapter_List3);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}