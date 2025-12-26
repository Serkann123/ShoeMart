package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.database.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnRegister;
    TextView tvGoLogin;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = new DatabaseHelper(this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnAction);
        tvGoLogin = findViewById(R.id.tvToggle);

        tvGoLogin.setOnClickListener(v ->
        {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    public void buRegister(View view) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Kullanıcı adı ve şifre boş olamaz", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean ok = db.Register(username, password);
        if (ok) {

            // yeni kaydolan kullanıcının id'sini al
            int userId=db.getUserId(username,password);

            getSharedPreferences("session",MODE_PRIVATE);
            getSharedPreferences("session", MODE_PRIVATE)
                    .edit()
                    .putInt("user_id", userId)
                    .putString("username", username)
                    .apply();

            Toast.makeText(this, "Kayıt başarılı.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(RegisterActivity.this, ProductListActivity.class);
            intent.putExtra("userInput", username);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Kayıt başarısız oldu", Toast.LENGTH_SHORT).show();
        }
    }
}
