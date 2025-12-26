package com.example.myapplication.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CartAdapter;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.models.CartItem;

import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    RecyclerView rvSepet;
    TextView textToplamTutar;

    DatabaseHelper db;
    CartAdapter adapter;
    Button btnSiparisiTamamla;
    List<CartItem> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            toolbar.setNavigationOnClickListener(v -> finish());
        }

        rvSepet = findViewById(R.id.rvSepet);
        textToplamTutar = findViewById(R.id.textToplamTutar);
        btnSiparisiTamamla = findViewById(R.id.btnSiparisiTamamla);

        rvSepet.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sp = getSharedPreferences("session", MODE_PRIVATE);
        int userId = sp.getInt("user_id", -1);

        db = new DatabaseHelper(this);
        cartItems = db.getCartByUser(userId);

        adapter = new CartAdapter(this, cartItems, () -> hesaplaVeYazdir());
        rvSepet.setAdapter(adapter);
        hesaplaVeYazdir();

        btnSiparisiTamamla.setOnClickListener(v -> {
            if (cartItems.isEmpty()) {
                Toast.makeText(this, "Sepet boş.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Sipariş onaylandı!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void hesaplaVeYazdir() {
        double toplam = 0;
        for (CartItem item : cartItems) {
            toplam += item.getFiyat();
        }
        textToplamTutar.setText("Toplam Tutar: " + toplam + " TL");
    }
}
