package com.example.myrepo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnLinearForm).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LinearFormActivity.class));
        });
    }
}