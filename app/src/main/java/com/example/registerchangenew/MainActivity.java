package com.example.registerchangenew;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.Manifest; // Helps with POST_NOTIFICATIONS stuff


//импорты для уведомления

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText inputText = findViewById(R.id.inputText);
        Button changeCaseButton = findViewById(R.id.changeCaseButton);
        final TextView resultText = findViewById(R.id.resultText);


//        changeCaseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String originalText = inputText.getText().toString();
//                String modifiedText = FunnyUpercase.toFunnyUpercase(originalText, MainActivity.this);
//                resultText.setText(modifiedText);
//            }
//        }); ТОЖЕ САМОЕ ЧТО НАПИСАНО СНИЗУ

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
        showNotification();

        checkForNotificationPermission();
    }

    //Код для Оповещений
    public static final int MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS = 1;

    private void checkForNotificationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // Если разрешение не предоставлено, запрашиваем его
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS);
        }
        // Если разрешение уже было предоставлено, мы можем пропустить запрос и сразу выполнить действие, требующее разрешения
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Разрешение было предоставлено
            } else {
                // Разрешение было отклонено. Если пользователь выбрал "Не спрашивать снова", мы не должны беспокоить его повторными запросами.
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.POST_NOTIFICATIONS)) {
                    // Пользователь выбрал "Не спрашивать снова". Мы можем показать объяснение здесь.
                }
                // В противном случае, ничего не делаем и ждем, пока пользователь сам запросит функциональность, требующую разрешения.
            }
        }
    }
    private void showNotification() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            // Создание интента, который откроет вашу активность при нажатии на уведомление
            Intent intent = new Intent(this, ProcessTextActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            // Построение уведомления
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Пример уведомления")
                    .setContentText("Нажмите, чтобы открыть приложение")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            // Получаем экземпляр NotificationManagerCompat и отправляем уведомление
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(1, builder.build());
        } else {
            // Если разрешение не предоставлено, запрашиваем его
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS);
        }
    }


}

