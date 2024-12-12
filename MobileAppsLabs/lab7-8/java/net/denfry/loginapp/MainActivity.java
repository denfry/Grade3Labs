package net.denfry.loginapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText usernameEditText, passwordEditText;
    Button loginButton;
    TextView registerTextView;
    DatabaseHelper databaseHelper;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerTextView = findViewById(R.id.registerTextView);
        databaseHelper = new DatabaseHelper(this);  // Инициализация DatabaseHelper

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
            showAlertDialog("Предупреждение", "Пожалуйста, введите имя пользователя и пароль.");
            return;
        }

        if (databaseHelper.isUserExists(inputUsername) && databaseHelper.isPasswordCorrect(inputUsername, inputPassword)) {
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
        } else {
            showAlertDialog("Предупреждение", "Неправильное имя пользователя или пароль.");
        }
    }

    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("ОК", (dialog, which) -> dialog.dismiss())
                .setCancelable(false)
                .show();
    }
}
