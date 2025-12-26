package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.models.CartItem;
import com.example.myapplication.models.Product;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "shop_app.db";

    public DatabaseHelper(Context context) {

        super(context, DB_NAME, null, 5);
    }

    // tablolar oluşturuluyor
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT," +
                "password TEXT)");

        db.execSQL("CREATE TABLE products (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "price REAL," +
                "image_name TEXT)");

        db.execSQL("CREATE TABLE cart(" +
                "sepet_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER," +
                "urun_adi TEXT," +
                "fiyat REAL," +
                "resim TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS products");
        db.execSQL("DROP TABLE IF EXISTS cart");
        onCreate(db);
    }

    // Giriş yapan kullanıcının Id değerini almak için yazılmıştır bu fonksiyon.
    public int getUserId(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id FROM users WHERE username=? and password=?",
                new String[]{username, password});

        int userId = -1;

        if (c.moveToFirst()) {
            userId = c.getInt(0); // id'yi al.
        }
        c.close();
        return userId;
    }

    public boolean Login(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT 1 FROM users WHERE username=? AND password=?", new String[]{username, password});

        boolean ok = c.moveToFirst();
        c.close();
        return ok;
    }

    public boolean Register(String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);

        cv.put("password", password);

        long res = db.insert("users", null, cv);

        return res != -1;
    }

    public long addProduct(String name, double price, String imageName) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", name);
        cv.put("price", price);
        cv.put("image_name", imageName);

        return db.insert("products", null, cv);
    }

    public List<Product> getAllProducts() {

        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM products", null);

        // veri varmı yoksa girme diye kontrol ediyoruz
        if (cursor.moveToFirst()) {
            do {
                Product p = new Product();
                p.setId(cursor.getInt(0));
                p.setName(cursor.getString(1));
                p.setPrice(cursor.getDouble(2));
                p.setImageName(cursor.getString(3));

                productList.add(p);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return productList;
    }

    // Ürün eklemek için bir panel yapmamız lazımdı onu yapmadık biz kullanıcı tarafı olduğumuz için
    // bunun için verileri kendimiz ekledik.
    public void seedProductsIfEmpty() {
        if (getAllProducts().size() == 0) {
            addProduct("Nike Air Max", 2500.0, "ayakkabi1");
            addProduct("Adidas Superstar", 1800.0, "ayakkabi2");
            addProduct("Puma Suede", 1200.0, "ayakkabi3");
            addProduct("Nike Air Force 1", 2700.0, "ayakkabi4");
            addProduct("Adidas Stan Smith", 2000.0, "ayakkabi5");
            addProduct("Puma RS-X", 2300.0, "ayakkabi6");
            addProduct("Nike Jordan", 3500.0, "ayakkabi7");
            addProduct("Adidas Ultraboost", 3200.0, "ayakkabi8");
            addProduct("Puma Future Rider", 2100.0, "ayakkabi9");
            addProduct("Nike Pegasus", 2600.0, "ayakkabi10");

            addProduct("Adidas NMD", 2400.0, "ayakkabi1");
            addProduct("Puma Cali", 1900.0, "ayakkabi2");
            addProduct("Nike Blazer", 2200.0, "ayakkabi3");
            addProduct("Adidas Gazelle", 2100.0, "ayakkabi4");
            addProduct("Puma Smash", 1700.0, "ayakkabi5");
        }
    }


    // Swpete ekle
    public long addToCart(int userId, String urunAdi, double fiyat, String resim) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("user_id", userId);
        cv.put("urun_adi", urunAdi);
        cv.put("fiyat", fiyat);
        cv.put("resim", resim);

        return db.insert("cart", null, cv);
    }

    // Sepeti getir kullanıcıya göre
    public List<CartItem> getCartByUser(int userId) {

        SQLiteDatabase db = getReadableDatabase();
        List<CartItem> cartList = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * from cart WHERE user_id=?",
                new String[]{String.valueOf(userId)});

        if (c.moveToFirst()) {
            do {
                CartItem item = new CartItem();
                item.setSepetId(c.getInt(0));
                item.setUserId(c.getInt(1));
                item.setUrunAdi(c.getString(2));
                item.setFiyat(c.getDouble(3));
                item.setResim(c.getString(4));
                cartList.add(item);
            }
            while (c.moveToNext());
        }
        c.close();
        return cartList;
    }

    // Sepetten sil
    public int deleteCartItem(int sepetId) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("cart", "sepet_id=?", new String[]{String.valueOf(sepetId)});
    }
}
