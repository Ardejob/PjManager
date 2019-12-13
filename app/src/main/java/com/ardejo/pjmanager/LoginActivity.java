package com.ardejo.pjmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView txtResetPassword = findViewById(R.id.txt_resetPassword);
        TextView txtSignUp = findViewById(R.id.txt_signUp);

        txtResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                TODO: Implement reset password onClick Listener
                 */
                Toast.makeText(LoginActivity.this, "Reset Password", Toast.LENGTH_SHORT).show();
            }
        });

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class));
                overridePendingTransition(0,0);
            }
        });

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                overridePendingTransition(0,0);
                finish();
            }
        });
    }
}
