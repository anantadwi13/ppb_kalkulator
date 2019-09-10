package com.anantadwi13.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int TYPE_CALCULATE = 0;
    private static final int TYPE_ADDITION = 1;
    private static final int TYPE_SUBSTRACT = 2;
    private static final int TYPE_MULTIPLICATION = 3;
    private static final int TYPE_DIVISION = 4;

    private String textSimpan = "";
    private String textHasil = "0";
    private BigDecimal hasil = new BigDecimal("0");
    private int lastOperationType = TYPE_ADDITION;

    TextView tvHasil, tvSimpan;
    HorizontalScrollView svHasil, svSimpan;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn000,
            btnComma, btnAC, btnBack, btnCalculate, btnAddition, btnSubtract, btnMultiple, btnDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        svHasil = findViewById(R.id.containerHasil);
        svSimpan = findViewById(R.id.containerSimpan);
        tvHasil = findViewById(R.id.tvHasil);
        tvSimpan = findViewById(R.id.tvSimpan);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn000 = findViewById(R.id.btn000);
        btnComma = findViewById(R.id.btnComma);
        btnAC = findViewById(R.id.btnAC);
        btnBack = findViewById(R.id.btnBack);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnAddition = findViewById(R.id.btnAddition);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiple = findViewById(R.id.btnMultiple);
        btnDiv = findViewById(R.id.btnDiv);

        btn000.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnComma.setOnClickListener(this);
        btnAC.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnCalculate.setOnClickListener(this);
        btnAddition.setOnClickListener(this);
        btnSubtract.setOnClickListener(this);
        btnMultiple.setOnClickListener(this);
        btnDiv.setOnClickListener(this);

        tvHasil.setText(textHasil);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn000:
                textHasil += "000";
                break;
            case R.id.btn0:
                textHasil += "0";
                break;
            case R.id.btn1:
                textHasil += "1";
                break;
            case R.id.btn2:
                textHasil += "2";
                break;
            case R.id.btn3:
                textHasil += "3";
                break;
            case R.id.btn4:
                textHasil += "4";
                break;
            case R.id.btn5:
                textHasil += "5";
                break;
            case R.id.btn6:
                textHasil += "6";
                break;
            case R.id.btn7:
                textHasil += "7";
                break;
            case R.id.btn8:
                textHasil += "8";
                break;
            case R.id.btn9:
                textHasil += "9";
                break;
            case R.id.btnComma:
                try {
                    Double.parseDouble(textHasil + ".0");
                    if (textHasil.length() == 0)
                        textHasil += "0.";
                    else
                        textHasil += ".";
                } catch (Exception ignored){

                }
                break;
            case R.id.btnAC:
                textHasil = "";
                textSimpan = "";
                hasil = new BigDecimal("0");
                lastOperationType = TYPE_ADDITION;
                break;
            case R.id.btnBack:
                if (textHasil.length()>0)
                    textHasil = textHasil.substring(0, textHasil.length()-1);
                break;
            case R.id.btnCalculate:
                if (new BigDecimal(textHasil).doubleValue() != 0.0)
                    hasil = calculate(new BigDecimal(textHasil), lastOperationType);
                textSimpan = " = " + checkNilai(hasil);
                textHasil = checkNilai(hasil);
                lastOperationType = TYPE_CALCULATE;
                break;
            case R.id.btnAddition:
                if (new BigDecimal(textHasil).doubleValue() != 0.0)
                    hasil = calculate(new BigDecimal(textHasil), lastOperationType);
                textSimpan = checkNilai(hasil) + " + ";
                textHasil = "";
                lastOperationType = TYPE_ADDITION;
                break;
            case R.id.btnSubtract:
                if (new BigDecimal(textHasil).doubleValue() != 0.0)
                    hasil = calculate(new BigDecimal(textHasil), lastOperationType);
                textSimpan = checkNilai(hasil) + " - ";
                textHasil = "";
                lastOperationType = TYPE_SUBSTRACT;
                break;
            case R.id.btnMultiple:
                if (new BigDecimal(textHasil).doubleValue() != 0.0)
                    hasil = calculate(new BigDecimal(textHasil), lastOperationType);
                textSimpan = checkNilai(hasil) + " x ";
                textHasil = "";
                lastOperationType = TYPE_MULTIPLICATION;
                break;
            case R.id.btnDiv:
                if (new BigDecimal(textHasil).doubleValue() != 0.0)
                    hasil = calculate(new BigDecimal(textHasil), lastOperationType);
                textSimpan = checkNilai(hasil) + " / ";
                textHasil = "";
                lastOperationType = TYPE_DIVISION;
                break;
        }
        try {
            textHasil = checkNilai(textHasil);
            tvHasil.setText(textHasil);
            tvSimpan.setText(textSimpan);
            svHasil.fullScroll(View.FOCUS_RIGHT);
            svSimpan.fullScroll(View.FOCUS_RIGHT);
            Log.d("DEBUG", "onClick: textHasil="+textHasil+" textSimpan="+textSimpan+" hasil="+hasil);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private BigDecimal calculate(BigDecimal nilai, int operationType){
        switch (operationType){
            case TYPE_ADDITION:
                return hasil.add(nilai);
            case TYPE_CALCULATE:
                return hasil;
            case TYPE_DIVISION:
                hasil = hasil.add(new BigDecimal("0.0"));
                return hasil.divide(nilai, RoundingMode.HALF_UP);
            case TYPE_MULTIPLICATION:
                return hasil.multiply(nilai);
            case TYPE_SUBSTRACT:
                return hasil.subtract(nilai);
            default:
                return hasil;
        }
    }

    private String checkNilai(String nilai){
        if (nilai.length() > 0) {
            double hasilDouble = Double.parseDouble(nilai);
            double hasilBulat = Math.floor(hasilDouble);
            if (hasilDouble - hasilBulat == 0 && !nilai.contains("."))
                return String.format(Locale.US, "%d",(long) hasilBulat);
            return nilai;
        }
        else
            return "0";
    }

    private String checkNilai(BigDecimal nilai){
        return String.valueOf(nilai);
//        double hasilBulat = Math.floor(nilai);
//        if (nilai - hasilBulat == 0)
//            return String.format(Locale.US, "%d",(long) hasilBulat);
//        return String.format(Locale.US, "%.12f", nilai);
    }
}
