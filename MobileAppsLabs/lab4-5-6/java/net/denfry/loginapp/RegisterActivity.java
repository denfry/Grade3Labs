package net.denfry.loginapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    EditText registerUsernameEditText, registerPasswordEditText;
    Button registerButton;
    Button backButton;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerUsernameEditText = findViewById(R.id.registerUsernameEditText);
        registerPasswordEditText = findViewById(R.id.registerPasswordEditText);
        registerButton = findViewById(R.id.registerButton);
        backButton = findViewById(R.id.backButton);

        Animation fadeInButton = AnimationUtils.loadAnimation(this, R.anim.fade_in_button);
        registerButton.startAnimation(fadeInButton);

        Animation scaleUpButton = AnimationUtils.loadAnimation(this, R.anim.scale_up_button);
        registerButton.setOnTouchListener((v, event) -> {
            if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                v.startAnimation(scaleUpButton);
            }
            return false;
        });


        Animation fadeInBackButton = AnimationUtils.loadAnimation(this, R.anim.fade_in_button);
        backButton.startAnimation(fadeInBackButton);

        Animation scaleUpBackButton = AnimationUtils.loadAnimation(this, R.anim.scale_up_button);
        backButton.setOnTouchListener((v, event) -> {
            if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                v.startAnimation(scaleUpBackButton);
            }
            return false;
        });

        backButton.setOnClickListener(v -> goBack());

        registerButton.setOnClickListener(v -> registerUser());
    }

    private void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void registerUser() {
        String username = registerUsernameEditText.getText().toString();
        String password = registerPasswordEditText.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Введите имя пользователя и пароль", Toast.LENGTH_SHORT).show();
            return;
        }

        String userData = username + "," + password + "\n";

        try {
            FileOutputStream fos = openFileOutput("users.txt", Context.MODE_APPEND);
            fos.write(userData.getBytes());
            fos.close();
            Toast.makeText(this, "Регистрация успешна!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Ошибка сохранения данных", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
