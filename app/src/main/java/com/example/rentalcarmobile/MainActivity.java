package com.example.rentalcarmobile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rentalcarmobile.databinding.ActivityMainBinding;
import com.example.rentalcarmobile.errorhandling.ErrorHandler;
import com.example.rentalcarmobile.fragments.BaseFragment;
import com.example.rentalcarmobile.fragments.HomeFragment;
import com.example.rentalcarmobile.fragments.ProfileFragment;
import com.example.rentalcarmobile.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    @NonNull
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new ProfileFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.profile) {
                replaceFragment(new ProfileFragment());
            } else if (item.getItemId() == R.id.settings) {
                replaceFragment(new SettingsFragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        if (currentFragment instanceof HomeFragment) {
            finish();
        } else {
            binding.bottomNavigationView.setSelectedItemId(R.id.profile);
        }
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof HomeFragment ||
                fragment instanceof ProfileFragment ||
                fragment instanceof SettingsFragment) {
            ((BaseFragment) fragment).setErrorHandler(ErrorHandler.getInstance());
        }
    }
}
