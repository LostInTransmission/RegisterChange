package com.example.registerchangenew;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText inputText = findViewById(R.id.inputText);
        Button changeCaseButton = findViewById(R.id.changeCaseButton);
        final TextView resultText = findViewById(R.id.resultText);

        changeCaseButton.setOnClickListener(view -> {
            String originalText = inputText.getText().toString();
            String modifiedText = FunnyUpercase.toFunnyUpercase(originalText, MainActivity.this);
            resultText.setText(modifiedText);
        });

        Button settingsButton = findViewById(R.id.SettingsButton);

        settingsButton.setOnClickListener(v -> {
            // Создаем Intent для запуска SettingsActivity
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent); // Запуск SettingsActivity
        });
    }

}

