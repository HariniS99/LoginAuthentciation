package com.example.loginauthentciation;
import utils.AuthHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    AuthHelper auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = new AuthHelper(this);
        username = findViewById(R.id.usernameSignIn);
        password = findViewById(R.id.passwordSignIn);
    }

    public void createNewAccount(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public void signIn(View view) {
        if(auth.loginUser(username.getText().toString(), password.getText().toString())) {
            Toast.makeText(this, "success!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else
            Toast.makeText(this, "username/password is incorrect", Toast.LENGTH_SHORT).show();

    }
}