package net.denfry.loginapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText registerUsernameEditText, registerPasswordEditText;
    Button registerButton, backButton;
    DatabaseHelper databaseHelper;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerUsernameEditText = findViewById(R.id.registerUsernameEditText);
        registerPasswordEditText = findViewById(R.id.registerPasswordEditText);
        registerButton = findViewById(R.id.registerButton);
        backButton = findViewById(R.id.backButton);

        databaseHelper = new DatabaseHelper(this);

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
            showAlertDialog("Предупреждение", "Заполните все поля");
            return;
        }

        if (databaseHelper.isUserExists(username)) {
            showAlertDialog("Предупреждение", "Пользователь с таким именем уже существует");
            return;
        }

        boolean isRegistered = databaseHelper.registerUser(username, password);
        if (isRegistered) {
            showAlertDialog("Успех", "Пользователь успешно зарегистрирован");
            goBack();
        } else {
            showAlertDialog("Ошибка", "Не удалось зарегистрировать пользователя");
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
