package com.example.rentalcarmobile.fragments;

import androidx.fragment.app.Fragment;

import com.example.rentalcarmobile.errorhandling.ErrorHandler;

public abstract class BaseFragment extends Fragment {
    protected ErrorHandler errorHandler;

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }
}
