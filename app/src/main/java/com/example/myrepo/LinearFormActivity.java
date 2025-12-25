package com.example.myrepo;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class LinearFormActivity extends AppCompatActivity {

    EditText etName, etPassword, etAddress, etAge;
    RadioGroup rgGender;
    Button btnDob, btnSubmit;
    Spinner spinnerState;
    TextView tvResult;
    String selectedDob = "Not selected";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_form);

        // Find views
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        etAddress = findViewById(R.id.etAddress);
        etAge = findViewById(R.id.etAge);
        rgGender = findViewById(R.id.rgGender);
        btnDob = findViewById(R.id.btnDob);
        spinnerState = findViewById(R.id.spinnerState);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvResult = findViewById(R.id.tvResult);

        // Setup Spinner (States of India)
        String[] states = {"Select State","Andhra Pradesh","Maharashtra", "Karnataka", "Tamil Nadu", "Gujarat", "Delhi", "Kerala", "UP", "Punjab"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, states);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(adapter);

        // Date Picker
        btnDob.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(this, (view, y, m, d) -> {
                selectedDob = d + "/" + (m + 1) + "/" + y;
                btnDob.setText("DOB: " + selectedDob);
            }, year, month, day);
            dialog.show();
        });

        // Submit Button
        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String password = etPassword.getText().toString();
            String address = etAddress.getText().toString().trim();
            String age = etAge.getText().toString().trim();

            if (name.isEmpty() || password.isEmpty() || address.isEmpty() || age.isEmpty() || selectedDob.equals("Not selected") || spinnerState.getSelectedItemPosition() == 0) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Get Gender
            int selectedId = rgGender.getCheckedRadioButtonId();
            String gender = "Not selected";
            if (selectedId != -1) {
                RadioButton selected = findViewById(selectedId);
                gender = selected.getText().toString();
            }

            String state = spinnerState.getSelectedItem().toString();

            // Build result string
            String result = "=== Submitted Data ===\n" +
                    "Name: " + name + "\n" +
                    "Password: " + password + "\n" +
                    "Address: " + address + "\n" +
                    "Gender: " + gender + "\n" +
                    "Age: " + age + "\n" +
                    "Date of Birth: " + selectedDob + "\n" +
                    "State: " + state;

            tvResult.setText(result);
        });
    }
}