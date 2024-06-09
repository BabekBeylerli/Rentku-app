package com.example.rentalcarmobile.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentalcarmobile.R;
import com.example.rentalcarmobile.database.DatabaseHelper;
import com.example.rentalcarmobile.errorhandling.ErrorHandler;

public class SignUpActivity extends AppCompatActivity {

    private ErrorHandler errorHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        errorHandler = ErrorHandler.getInstance();

        TextView haveAccount = findViewById(R.id.have_account);
        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    public void sign_up(View view) {
        try {
            EditText fullNameEditText = findViewById(R.id.user_fullName);
            EditText emailEditText = findViewById(R.id.user_email);
            EditText passwordEditText = findViewById(R.id.user_password);
            EditText confirmEditText = findViewById(R.id.re_user_password);

            String fullName = fullNameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirm = confirmEditText.getText().toString();

            if (fullName.isEmpty()) {
                Toast.makeText(this, "Enter Fullname", Toast.LENGTH_SHORT).show();
            } else if (email.isEmpty()) {
                Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            } else if (confirm.isEmpty()) {
                Toast.makeText(this, "Confirm Password", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirm)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                DatabaseHelper dbHelper = new DatabaseHelper(this);
                dbHelper.addUser(email, password); // Add user to the database
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        } catch (Exception e) {
            errorHandler.handleException(this, e);
        }
    }
}
