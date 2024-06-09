package com.example.rentalcarmobile.screens;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentalcarmobile.MainActivity;
import com.example.rentalcarmobile.R;
import com.example.rentalcarmobile.database.DatabaseHelper;
import com.example.rentalcarmobile.database.UserContract;
import com.example.rentalcarmobile.errorhandling.ErrorHandler;

public class LoginActivity extends AppCompatActivity {

    private ErrorHandler errorHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        errorHandler = ErrorHandler.getInstance();

        TextView createAccount = findViewById(R.id.dont_have_account);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();
            }
        });
    }

    public void login(View view) {
        try {
            EditText emailEditText = findViewById(R.id.user_email);
            EditText passwordEditText = findViewById(R.id.user_password);

            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (email.isEmpty()) {
                Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            } else {
                DatabaseHelper dbHelper = new DatabaseHelper(this);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String[] projection = {
                        UserContract.UserEntry.USER_ID,
                        UserContract.UserEntry.COLUMN_EMAIL,
                        UserContract.UserEntry.COLUMN_PASSWORD
                };
                String selection = UserContract.UserEntry.COLUMN_EMAIL + " = ? AND " +
                        UserContract.UserEntry.COLUMN_PASSWORD + " = ?";
                String[] selectionArgs = { email, password };

                Cursor cursor = db.query(
                        UserContract.UserEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null
                );

                boolean userExists = cursor.getCount() > 0;
                cursor.close();
                db.close();

                if (userExists) {
                    // Store email in SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user_email", email);
                    editor.apply();

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            errorHandler.handleException(this, e);
        }
    }
}
