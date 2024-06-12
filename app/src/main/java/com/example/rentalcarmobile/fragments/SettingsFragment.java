package com.example.rentalcarmobile.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.rentalcarmobile.R;
import com.example.rentalcarmobile.database.DatabaseHelper;
import com.example.rentalcarmobile.screens.LoginActivity;
import com.example.rentalcarmobile.screens.ProfileActivity;

public class SettingsFragment extends BaseFragment {

    private DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        databaseHelper = new DatabaseHelper(getContext());
        Button logoutButton = view.findViewById(R.id.LogOut);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        Button deleteAccountButton = view.findViewById(R.id.DeleteAcc);
        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog();
            }
        });

        ImageView imageView7 = view.findViewById(R.id.imageView7);

        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }

    private void logout() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    private void showDeleteConfirmationDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("Hesabı Sil")
                .setMessage("Hesabınızı silmek istediğinizden emin misiniz? Bu işlem geri alınamaz.")
                .setPositiveButton("Sil", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteAccount();
                    }
                })
                .setNegativeButton("İptal", null)
                .show();
    }

    private void deleteAccount() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user_prefs", getActivity().MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("user_email", null);

        if (userEmail != null) {
            databaseHelper.deleteUser(userEmail);
            Toast.makeText(getContext(), "Hesap başarıyla silindi", Toast.LENGTH_SHORT).show();
            logout();
        } else {
            Toast.makeText(getContext(), "Hata: Kullanıcı bilgisi bulunamadı", Toast.LENGTH_SHORT).show();
        }
    }
}
