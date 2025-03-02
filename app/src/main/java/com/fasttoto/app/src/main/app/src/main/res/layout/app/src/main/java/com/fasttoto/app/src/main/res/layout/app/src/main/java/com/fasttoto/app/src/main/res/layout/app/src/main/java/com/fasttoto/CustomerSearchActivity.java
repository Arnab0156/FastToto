package com.fasttoto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class CustomerSearchActivity extends AppCompatActivity {

    private EditText etSearch;
    private Button btnSearch;
    private ListView listTotoOwners;
    private List<String> totoOwnersList;
    private ArrayAdapter<String> adapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_search);

        // UI Elements
        etSearch = findViewById(R.id.et_search);
        btnSearch = findViewById(R.id.btn_search);
        listTotoOwners = findViewById(R.id.list_toto_owners);

        // SharedPreferences থেকে ডাটা লোড করা
        sharedPreferences = getSharedPreferences("TotoOwnerPrefs", MODE_PRIVATE);
        loadTotoOwners();  

        // সার্চ বাটনের ক্লিক ইভেন্ট
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchTotoOwners();
            }
        });

        // লিস্ট আইটেম ক্লিক করলে কল বা মেসেজ অপশন দেখাবে
        listTotoOwners.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedOwner = totoOwnersList.get(position);
                showCallMessageOptions(selectedOwner);
            }
        });
    }

    // SharedPreferences থেকে Toto Owners লোড করা
    private void loadTotoOwners() {
        totoOwnersList = new ArrayList<>();
        String name = sharedPreferences.getString("owner_name", "Unknown");
        String phone = sharedPreferences.getString("owner_phone", "No Number");
        if (!name.equals("Unknown")) {
            totoOwnersList.add(name + " - " + phone);
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, totoOwnersList);
        listTotoOwners.setAdapter(adapter);
    }

    // সার্চ অপশন
    private void searchTotoOwners() {
        String query = etSearch.getText().toString().toLowerCase();
        if (query.isEmpty()) {
            Toast.makeText(this, "Enter name or phone number to search!", Toast.LENGTH_SHORT).show();
            return;
        }
        List<String> filteredList = new ArrayList<>();
        for (String owner : totoOwnersList) {
            if (owner.toLowerCase().contains(query)) {
                filteredList.add(owner);
            }
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filteredList);
        listTotoOwners.setAdapter(adapter);
    }

    // কল বা মেসেজ অপশন দেখানো
    private void showCallMessageOptions(final String ownerInfo) {
        String[] parts = ownerInfo.split(" - ");
        if (parts.length < 2) return;
        final String phoneNumber = parts[1];

        Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        startActivity(callIntent);
    }
}
