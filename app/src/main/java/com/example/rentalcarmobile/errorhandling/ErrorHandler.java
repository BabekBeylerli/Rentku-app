package com.example.rentalcarmobile.errorhandling;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;

public class ErrorHandler {

    // Singleton instance
    private static ErrorHandler instance;

    // Private constructor to prevent instantiation
    private ErrorHandler() {
    }

    // Method to get singleton instance
    public static ErrorHandler getInstance() {
        if (instance == null) {
            instance = new ErrorHandler();
        }
        return instance;
    }

    // Method to handle exceptions and show error message
    public void handleException(Context context, Exception e) {
        // Check if the exception is an instance of a specific type, you can customize this part
        if (e instanceof IOException) {
            // Handle IO exception
            Toast.makeText(context, "IO Exception occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } else if (e instanceof NullPointerException) {
            // Handle null pointer exception
            Toast.makeText(context, "Null Pointer Exception occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            // Handle any other type of exception
            Toast.makeText(context, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
