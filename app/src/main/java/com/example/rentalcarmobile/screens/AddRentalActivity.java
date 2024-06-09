package com.example.rentalcarmobile.screens;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import com.example.rentalcarmobile.R;
import com.example.rentalcarmobile.dao.database.RentalCarDatabase;
import com.example.rentalcarmobile.dao.entity.RentalCar;
import com.example.rentalcarmobile.errorhandling.ErrorHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AddRentalActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PERMISSION_REQUEST_CODE = 2;
    private RentalCarDatabase db;
    private Uri selectedImageUri;
    private ImageView selectedPhotoImageView;
    private ErrorHandler errorHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rental);

        errorHandler = ErrorHandler.getInstance();

        db = Room.databaseBuilder(getApplicationContext(),
                RentalCarDatabase.class, "rental-car-database").allowMainThreadQueries().build();

        selectedPhotoImageView = findViewById(R.id.selectedPhoto);
        Button btnSelectPhoto = findViewById(R.id.selectPhoto_button);
        btnSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        Button btnAddRental = findViewById(R.id.addRental_button);
        btnAddRental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRentalCar();
            }
        });
    }

    private void openFileChooser() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_CODE);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            selectedPhotoImageView.setImageURI(selectedImageUri);
        }
    }

    private void addRentalCar() {
        try {
            EditText rentalNameEditText = findViewById(R.id.rentalName);
            EditText rentalModelEditText = findViewById(R.id.rentalModel);
            EditText rentalYearEditText = findViewById(R.id.rentalYear);
            EditText rentalKmEditText = findViewById(R.id.rentalKm);
            EditText rentalPriceEditText = findViewById(R.id.rentalPrice);

            String rentalName = rentalNameEditText.getText().toString();
            String rentalModel = rentalModelEditText.getText().toString();
            String rentalYear = rentalYearEditText.getText().toString();
            String rentalKm = rentalKmEditText.getText().toString();
            String rentalPrice = rentalPriceEditText.getText().toString();

            if (rentalName.isEmpty() || rentalModel.isEmpty() || rentalYear.isEmpty() ||
                    rentalKm.isEmpty() || rentalPrice.isEmpty() || selectedImageUri == null) {
                Toast.makeText(this, "Please fill in all fields and select a photo", Toast.LENGTH_SHORT).show();
            } else {
                RentalCar rentalCar = new RentalCar();
                rentalCar.name = rentalName;
                rentalCar.model = rentalModel;
                rentalCar.year = rentalYear;
                rentalCar.km = rentalKm;
                rentalCar.price = rentalPrice;
                rentalCar.photoPath = saveImageToFile(selectedImageUri); // Save the photo and get the path

                db.rentalCarDao().insert(rentalCar);

                Toast.makeText(this, "Rental Car Added", Toast.LENGTH_SHORT).show();

                finish();
            }
        } catch (Exception e) {
            errorHandler.handleException(this, e);
        }
    }

    private String saveImageToFile(Uri imageUri) {
        try {
            // Dosya yolunu oluşturun
            File imageFile = new File(getFilesDir(), "rental_car_image_" + System.currentTimeMillis() + ".jpg");
            OutputStream outputStream = new FileOutputStream(imageFile);

            // Girdi akışını açın
            InputStream inputStream = getContentResolver().openInputStream(imageUri);

            // Dosyayı kopyalayın
            byte[] buffer = new byte[1024]; // 1KB'lık bir buffer oluşturun
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Dosya yolunu kaydedin
            outputStream.close();
            inputStream.close();
            return imageFile.getAbsolutePath();
        } catch (IOException e) {
            errorHandler.handleException(this, e);
            return null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // İzinler verilirse fotoğraf seçme işlemini başlat
                openFileChooser();
            } else {
                // İzinler verilmezse kullanıcıya uyarı göster
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
