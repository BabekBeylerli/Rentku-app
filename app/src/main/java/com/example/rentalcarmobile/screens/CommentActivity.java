package com.example.rentalcarmobile.screens;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rentalcarmobile.R;

public class CommentActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment);

        // XML dosyasındaki öğeleri bağla
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        // Buton tıklama olayını işleyin
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                Toast.makeText(CommentActivity.this, "You entered: " + text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

