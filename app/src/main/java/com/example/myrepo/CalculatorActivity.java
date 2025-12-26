package com.example.myrepo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    TextView tvDisplay;
    String current = "0";
    String previous = "";
    String operator = "";
    boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        tvDisplay = findViewById(R.id.tvDisplay);

        // Back button
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // Numbers
        int[] numberIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDecimal};
        for (int id : numberIds) {
            findViewById(id).setOnClickListener(v -> appendNumber(((Button) v).getText().toString()));
        }

        // Operators
        findViewById(R.id.btnAdd).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.btnSubtract).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.btnMultiply).setOnClickListener(v -> setOperator("×"));
        findViewById(R.id.btnDivide).setOnClickListener(v -> setOperator("÷"));

        // Equals
        findViewById(R.id.btnEquals).setOnClickListener(v -> calculate());

        // AC (Clear All)
        findViewById(R.id.btnAC).setOnClickListener(v -> clearAll());

        // Delete (←)
        findViewById(R.id.btnDelete).setOnClickListener(v -> deleteLast());

        // %
        findViewById(R.id.btnPercent).setOnClickListener(v -> percent());

        // +/-
        findViewById(R.id.btnPlusMinus).setOnClickListener(v -> plusMinus());
    }

    private void appendNumber(String num) {
        if (isOperatorPressed || current.equals("0")) {
            current = num;
            isOperatorPressed = false;
        } else {
            current += num;
        }
        updateDisplay();
    }

    private void setOperator(String op) {
        if (!current.isEmpty()) {
            if (!previous.isEmpty() && !isOperatorPressed) calculate();
            previous = current;
            current = "0";
        }
        operator = op;
        isOperatorPressed = true;
    }

    private void calculate() {
        if (!previous.isEmpty() && !current.isEmpty() && !operator.isEmpty()) {
            double prev = Double.parseDouble(previous);
            double curr = Double.parseDouble(current);
            double result = 0;

            switch (operator) {
                case "+": result = prev + curr; break;
                case "-": result = prev - curr; break;
                case "×": result = prev * curr; break;
                case "÷": result = prev / curr; break;
            }

            current = String.valueOf(result);
            if (current.endsWith(".0")) current = current.replace(".0", "");
            previous = "";
            operator = "";
            isOperatorPressed = true;
            updateDisplay();
        }
    }

    private void clearAll() {
        current = "0";
        previous = "";
        operator = "";
        updateDisplay();
    }

    private void deleteLast() {
        if (current.length() > 1) {
            current = current.substring(0, current.length() - 1);
        } else {
            current = "0";
        }
        updateDisplay();
    }

    private void percent() {
        if (!current.isEmpty()) {
            double val = Double.parseDouble(current) / 100;
            current = String.valueOf(val);
            if (current.endsWith(".0")) current = current.replace(".0", "");
            updateDisplay();
        }
    }

    private void plusMinus() {
        if (!current.equals("0")) {
            if (current.startsWith("-")) {
                current = current.substring(1);
            } else {
                current = "-" + current;
            }
            updateDisplay();
        }
    }

    private void updateDisplay() {
        tvDisplay.setText(current);
    }
}