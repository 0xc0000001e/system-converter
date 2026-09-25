package com.example.systemconverter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.graphics.Color;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int currentRadix = 10;

    private Button btnBase2, btnBase8, btnBase10, btnBase16;
    private EditText inputNumber;
    private TextView tvResultBinary, tvResultOctal, tvResultDecimal, tvResultHex;

    @Resource
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBase2 = findViewById(R.id.btnBase2);
        btnBase8 = findViewById(R.id.btnBase8);
        btnBase10 = findViewById(R.id.btnBase10);
        btnBase16 = findViewById(R.id.btnBase16);

        inputNumber = findViewById(R.id.inputNumber);

        tvResultBinary = findViewById(R.id.tvResultBinary);
        tvResultOctal = findViewById(R.id.tvResultOctal);
        tvResultDecimal = findViewById(R.id.tvResultDecimal);
        tvResultHex = findViewById(R.id.tvResultHex);

        btnBase2.setOnClickListener(v -> setRadix(2));
        btnBase8.setOnClickListener(v -> setRadix(8));
        btnBase10.setOnClickListener(v -> setRadix(10));
        btnBase16.setOnClickListener(v -> setRadix(16));

        inputNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                convert(s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        updateButtonStyles();
    }

    private void setRadix(int radix) {
        currentRadix = radix;
        updateButtonStyles();
        convert(inputNumber.getText().toString().trim());
    }

    private void updateButtonStyles() {
        btnBase2.setBackgroundColor(currentRadix == 2 ? Color.GRAY : Color.LTGRAY);
        btnBase8.setBackgroundColor(currentRadix == 8 ? Color.GRAY : Color.LTGRAY);
        btnBase10.setBackgroundColor(currentRadix == 10 ? Color.GRAY : Color.LTGRAY);
        btnBase16.setBackgroundColor(currentRadix == 16 ? Color.GRAY : Color.LTGRAY);
    }

    private void convert(String text) {
        if (text.isEmpty()) {
            tvResultBinary.setText("Двоичная (2): -");
            tvResultOctal.setText("Восьмеричная (8): -");
            tvResultDecimal.setText("Десятичная (10): -");
            tvResultHex.setText("Шестнадцатеричная (16): -");
            return;
        }

        try {
            int decimalValue = Integer.parseInt(text, currentRadix);

            tvResultBinary.setText("Двоичная (2): " + Integer.toBinaryString(decimalValue));
            tvResultOctal.setText("Восьмеричная (8): " + Integer.toOctalString(decimalValue));
            tvResultDecimal.setText("Десятичная (10): " + decimalValue);
            tvResultHex.setText("Шестнадцатеричная (16): " + Integer.toHexString(decimalValue).toUpperCase());

        } catch (NumberFormatException e) {
            tvResultBinary.setText("Ошибка ввода для базы " + currentRadix);
            tvResultOctal.setText("");
            tvResultDecimal.setText("");
            tvResultHex.setText("");
        }
    }
}
