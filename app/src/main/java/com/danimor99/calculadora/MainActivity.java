package com.danimor99.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private double value1 = Double.NaN;
    private double value2;
    private char operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.textViewResult);

        setNumberButtonListeners();
        setOperationButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberIds = { R.id.boton0, R.id.boton1, R.id.boton2, R.id.boton3, R.id.boton4,
                R.id.boton5, R.id.boton6, R.id.boton7, R.id.boton8, R.id.boton9 };

        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                textViewResult.append(button.getText().toString());
            }
        };

        for (int id : numberIds) {
            findViewById(id).setOnClickListener(numberListener);
        }
    }

    private void setOperationButtonListeners() {
        findViewById(R.id.botonMas).setOnClickListener(opListener('+'));
        findViewById(R.id.botonRestar).setOnClickListener(opListener('-'));
        findViewById(R.id.botonMultiplicar).setOnClickListener(opListener('*'));
        findViewById(R.id.botonDividir).setOnClickListener(opListener('/'));

        findViewById(R.id.botonIgual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
                textViewResult.setText(String.valueOf(value1));
                value1 = Double.NaN;
                operation = ' ';
            }
        });

        findViewById(R.id.botonBorrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewResult.setText("");
                value1 = Double.NaN;
                value2 = Double.NaN;
                operation = ' ';
            }
        });
    }

    private View.OnClickListener opListener(final char op) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Double.isNaN(value1)) {
                    calculate();
                } else {
                    value1 = Double.parseDouble(textViewResult.getText().toString());
                }
                operation = op;
                textViewResult.setText("");
            }
        };
    }

    private void calculate() {
        if (!Double.isNaN(value1)) {
            value2 = Double.parseDouble(textViewResult.getText().toString());
            switch (operation) {
                case '+':
                    value1 += value2;
                    break;
                case '-':
                    value1 -= value2;
                    break;
                case '*':
                    value1 *= value2;
                    break;
                case '/':
                    if (value2 != 0) value1 /= value2;
                    else value1 = Double.NaN; // Evitar división por cero
                    break;
            }
        } else {
            value1 = value2;
        }
    }
}