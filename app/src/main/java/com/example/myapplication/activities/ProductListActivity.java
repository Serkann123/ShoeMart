package com.example.myapplication.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.ProductAdapter;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.models.Product;

import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    TextView tvWelcomeUser;
    DatabaseHelper db;
    RecyclerView recyclerView;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        tvWelcomeUser = findViewById(R.id.tvWelcomeUser);

        SharedPreferences sp = getSharedPreferences("session", MODE_PRIVATE);
        String userName = sp.getString("username", "Kullanıcı");
        tvWelcomeUser.setText("Hoş geldin " + userName);

        db = new DatabaseHelper(this);

        db.seedProductsIfEmpty();

        recyclerView = findViewById(R.id.rvProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Product> allProducts = db.getAllProducts();

        adapter = new ProductAdapter(this, allProducts);

        recyclerView.setAdapter(adapter);
    }

    public void onGoToCartClick(View view) {
        Intent i = new Intent(ProductListActivity.this, CartActivity.class);
        startActivity(i);
    }
}
