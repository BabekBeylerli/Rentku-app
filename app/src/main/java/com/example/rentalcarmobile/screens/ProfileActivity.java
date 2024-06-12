package com.example.rentalcarmobile.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rentalcarmobile.R;

public class ProfileActivity extends AppCompatActivity {

    private ImageView imageProfile;
    private TextView textUsername;
    private TextView textFollowers;
    private TextView textFollowing;
    private TextView textUserInfo;
    private Button buttonEditProfile;
    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private ImageButton imageButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pp);

        // View'leri XML layout dosyasından bağlama
        imageProfile = findViewById(R.id.image_profile);
        textUsername = findViewById(R.id.text_username);
        textFollowers = findViewById(R.id.text_followers);
        textFollowing = findViewById(R.id.text_following);
        textUserInfo = findViewById(R.id.text_user_info);
        buttonEditProfile = findViewById(R.id.button_edit_profile);
        imageButton1 = findViewById(R.id.imageButton1);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton3 = findViewById(R.id.imageButton3);

        // Kullanıcı adı ve diğer bilgiler için örnek veriler ayarlama
        textUsername.setText("kullanici_adi");
        textFollowers.setText("100K");
        textFollowing.setText("200");
        textUserInfo.setText("Bilgi hakkında kısa bir açıklama...");

        // Buton tıklama olayları
        buttonEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile();
            }
        });

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCommentActivity();
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCommentActivity();
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCommentActivity();
            }
        });
    }

    private void editProfile() {
        // Profili düzenle işlemleri burada yapılacak
    }

    private void openCommentActivity() {
        Intent intent = new Intent(ProfileActivity.this, CommentActivity.class);
        startActivity(intent);
    }
}
