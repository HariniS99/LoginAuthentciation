package com.example.loginauthentciation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import utils.AuthHelper;

public class SignupActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    AuthHelper auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.usernameSignUp);
        password = findViewById(R.id.passwordSignUp);
        auth = new AuthHelper(this);
    }
    public void alreadyHaveAccount(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void signUp(View view) {
        if(auth.insertData(username.getText().toString(), password.getText().toString())) {
            Toast.makeText(this, "success!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else
            Toast.makeText(this, "something went wrong try again!!", Toast.LENGTH_SHORT).show();
    }
}