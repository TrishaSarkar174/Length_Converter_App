package com.hfad.lengthconverterapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etInputLength;
    private Spinner spinnerFromUnit, spinnerToUnit;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInputLength = findViewById(R.id.etInputLength);
        spinnerFromUnit = findViewById(R.id.spinnerFromUnit);
        spinnerToUnit = findViewById(R.id.spinnerToUnit);
        tvResult = findViewById(R.id.tvResult);
        Button btnConvert = findViewById(R.id.btnConvert);

        // Units for conversion
        String[] units = {"Meter", "Centimeter", "Kilometer", "Inch", "Yard", "Feet", "Mile"};

        // Adapter for Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);
        spinnerFromUnit.setAdapter(adapter);
        spinnerToUnit.setAdapter(adapter);

        // Set onClickListener for Convert button
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertLength();
            }
        });
    }

    private void convertLength() {
        // Get input value
        String input = etInputLength.getText().toString();
        if (input.isEmpty()) {
            tvResult.setText("Please enter a valid number");
            return;
        }

        double inputValue = Double.parseDouble(input);

        // Get selected units
        String fromUnit = spinnerFromUnit.getSelectedItem().toString();
        String toUnit = spinnerToUnit.getSelectedItem().toString();

        // Convert the value
        double conversionFactor = getConversionFactor(fromUnit, toUnit);
        double result = inputValue * conversionFactor;

        // Display result
        tvResult.setText("Result: " + result + " " + toUnit);
    }

    // Method to return conversion factor based on selected units
    private double getConversionFactor(String fromUnit, String toUnit) {
        // Conversion factors from Meter to other units
        double meterToCentimeter = 100;
        double meterToKilometer = 0.001;
        double meterToInch = 39.3701;
        double meterToYard = 1.09361;
        double meterToFeet = 3.28084;
        double meterToMile = 0.000621371;

        // Convert the fromUnit to meters first
        double fromToMeter = 1;
        switch (fromUnit) {
            case "Centimeter":
                fromToMeter = 1 / meterToCentimeter;
                break;
            case "Kilometer":
                fromToMeter = 1 / meterToKilometer;
                break;
            case "Inch":
                fromToMeter = 1 / meterToInch;
                break;
            case "Yard":
                fromToMeter = 1 / meterToYard;
                break;
            case "Feet":
                fromToMeter = 1 / meterToFeet;
                break;
            case "Mile":
                fromToMeter = 1 / meterToMile;
                break;
        }

        // Convert from meters to the target unit
        double meterToTarget = 1;
        switch (toUnit) {
            case "Centimeter":
                meterToTarget = meterToCentimeter;
                break;
            case "Kilometer":
                meterToTarget = meterToKilometer;
                break;
            case "Inch":
                meterToTarget = meterToInch;
                break;
            case "Yard":
                meterToTarget = meterToYard;
                break;
            case "Feet":
                meterToTarget = meterToFeet;
                break;
            case "Mile":
                meterToTarget = meterToMile;
                break;
        }

        return fromToMeter * meterToTarget;
    }
}



