package com.fasttoto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnSearchToto, btnRegisterOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Buttons
        btnSearchToto = findViewById(R.id.btn_search_toto);
        btnRegisterOwner = findViewById(R.id.btn_register_owner);

        // Button Click Listeners
        btnSearchToto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomerSearchActivity.class);
                startActivity(intent);
            }
        });

        btnRegisterOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TotoOwnerProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
