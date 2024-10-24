package net.denfry.loginapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    EditText registerUsernameEditText, registerPasswordEditText;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerUsernameEditText = findViewById(R.id.registerUsernameEditText);
        registerPasswordEditText = findViewById(R.id.registerPasswordEditText);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(v -> registerUser());
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
