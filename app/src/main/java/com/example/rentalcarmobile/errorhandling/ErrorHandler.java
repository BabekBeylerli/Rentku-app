package com.example.rentalcarmobile.errorhandling;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;

public class ErrorHandler {

    private static ErrorHandler instance;

    private ErrorHandler() {
    }

    public static ErrorHandler getInstance() {
        if (instance == null) {
            instance = new ErrorHandler();
        }
        return instance;
    }

    public void handleException(Context context, Exception e) {
        if (e instanceof IOException) {
            Toast.makeText(context, "IO Exception occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } else if (e instanceof NullPointerException) {
            Toast.makeText(context, "Null Pointer Exception occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
