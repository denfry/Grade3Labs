package net.denfry.loginapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText usernameEditText, passwordEditText;
    Button loginButton;
    TextView registerTextView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerTextView = findViewById(R.id.registerTextView);

        loginButton.setOnClickListener(v -> loginUser());
        registerTextView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        Animation fadeInButton = AnimationUtils.loadAnimation(this, R.anim.fade_in_button);
        loginButton.startAnimation(fadeInButton);

        Animation scaleUpButton = AnimationUtils.loadAnimation(this, R.anim.scale_up_button);
        loginButton.setOnTouchListener((v, event) -> {
            if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                v.startAnimation(scaleUpButton);
            }
            return false;
        });
    }

    private void loginUser() {
        String inputUsername = usernameEditText.getText().toString();
        String inputPassword = passwordEditText.getText().toString();

        if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
            Toast.makeText(this, "Введите имя пользователя и пароль", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            FileInputStream fis = openFileInput("users.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            boolean isValidUser = false;

            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData[0].equals(inputUsername) && userData[1].equals(inputPassword)) {
                    isValidUser = true;
                    break;
                }
            }

            fis.close();

            if (isValidUser) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Неверные данные", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Ошибка чтения данных", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
