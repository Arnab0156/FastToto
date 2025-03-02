package com.fasttoto;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TotoOwnerProfileActivity extends AppCompatActivity {

    private EditText etOwnerName, etOwnerPhone;
    private Spinner spinnerExperience;
    private Button btnSubmitProfile;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toto_owner_profile);

        // Initialize UI Elements
        etOwnerName = findViewById(R.id.et_owner_name);
        etOwnerPhone = findViewById(R.id.et_owner_phone);
        spinnerExperience = findViewById(R.id.spinner_experience);
        btnSubmitProfile = findViewById(R.id.btn_submit_profile);

        // SharedPreferences for saving profile data
        sharedPreferences = getSharedPreferences("TotoOwnerPrefs", MODE_PRIVATE);
        loadProfileData(); // Load existing data

        // Save profile on button click
        btnSubmitProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfileData();
            }
        });
    }

    private void saveProfileData() {
        String name = etOwnerName.getText().toString();
        String phone = etOwnerPhone.getText().toString();
        String experience = spinnerExperience.getSelectedItem().toString();

        if (name.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("owner_name", name);
        editor.putString("owner_phone", phone);
        editor.putString("owner_experience", experience);
        editor.apply();

        Toast.makeText(this, "Profile Saved Successfully!", Toast.LENGTH_SHORT).show();
    }

    private void loadProfileData() {
        String name = sharedPreferences.getString("owner_name", "");
        String phone = sharedPreferences.getString("owner_phone", "");
        String experience = sharedPreferences.getString("owner_experience", "");

        etOwnerName.setText(name);
        etOwnerPhone.setText(phone);
        // Spinner Experience Load করতে হবে (Extra Implementation)
    }
}
