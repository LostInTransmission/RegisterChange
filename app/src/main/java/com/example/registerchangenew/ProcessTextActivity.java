package com.example.registerchangenew;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ProcessTextActivity extends AppCompatActivity{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_process_text);

            CharSequence text = getIntent().getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT);
            //boolean isSwitchOn = SettingsActivity.swichState;
            // Обработайте текст
            if(text!=null){
                String processedText = FunnyUpercase.toFunnyUpercase(text.toString(),this);

                Intent data = new Intent();
                data.putExtra(Intent.EXTRA_PROCESS_TEXT, processedText);
                setResult(RESULT_OK, data);
                finish();
        }
    }

}
