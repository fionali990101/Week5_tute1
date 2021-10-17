package com.example.week2practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class CountryActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        String name = getIntent().getStringExtra("CountryName");

        TextView newCasess = findViewById(R.id.newCases);
        TextView totalCasess = findViewById(R.id.totalCases);
        TextView newDeathss = findViewById(R.id.newDeaths);
        TextView totalDeathss = findViewById(R.id.totalDeaths);
        TextView newCovereds = findViewById(R.id.newCovered);
        TextView totalCovereds = findViewById(R.id.totalCovered);
/*
        ArrayList<Country> countryArrayList = Country.getCountries();
        for (int i = 0; i < countryArrayList.size(); i++) {
            if (name.equals(countryArrayList.get(i).getCountryCode())) {
                //countryNamee.setText(String.valueOf(countryArrayList.get(i).getCountry()));
                newCasess.setText(String.valueOf(countryArrayList.get(i).getNewConfirmed()));
                totalCasess.setText(String.valueOf(countryArrayList.get(i).getTotalConfirmed()));
                newDeathss.setText(String.valueOf(countryArrayList.get(i).getNewDeaths()));
                totalDeathss.setText(String.valueOf(countryArrayList.get(i).getTotalDeaths()));
                newCovereds.setText(String.valueOf(countryArrayList.get(i).getNewRecovered()));
                totalCovereds.setText(String.valueOf(countryArrayList.get(i).getTotalRecovered()));

            }
        }
    */
       for (int i = 0; i< Country.getCountries().size(); i++) {
           if (name.equals(Country.getCountries().get(i).getCountry())) {
               newCasess.setText(String.valueOf(Country.getCountries().get(i).getNewConfirmed()));
               totalCasess.setText(String.valueOf(Country.getCountries().get(i).getTotalConfirmed()));
               newDeathss.setText(String.valueOf(Country.getCountries().get(i).getNewDeaths()));
               totalDeathss.setText(String.valueOf(Country.getCountries().get(i).getTotalDeaths()));
               newCovereds.setText(String.valueOf(Country.getCountries().get(i).getNewRecovered()));
               totalCovereds.setText(String.valueOf(Country.getCountries().get(i).getTotalConfirmed()));
           }
       }
    }
}