package net.denfry.loginapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    ImageView imageView;
    TextView welcomeTextView;
    Button logoutButton;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        imageView = findViewById(R.id.imageView);
        welcomeTextView = findViewById(R.id.welcomeTextView);
        logoutButton = findViewById(R.id.logoutButton);


        Animation scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        Animation fadeInButton = AnimationUtils.loadAnimation(this, R.anim.fade_in_button);
        logoutButton.startAnimation(fadeInButton);

        Animation scaleUpButton = AnimationUtils.loadAnimation(this, R.anim.scale_up_button);
        logoutButton.setOnTouchListener((v, event) -> {
            if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                v.startAnimation(scaleUpButton);
            }
            return false;
        });


        imageView.startAnimation(scaleDown);
        welcomeTextView.startAnimation(fadeIn);
        logoutButton.setOnClickListener(v -> logOut());
    }

    private void logOut() {

        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
