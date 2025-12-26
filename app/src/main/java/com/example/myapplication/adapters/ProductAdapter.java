package com.example.myapplication.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.models.Product;
import com.example.myapplication.R;

import java.util.List;

/*
 * ProductAdapter
 *
 * RecyclerView içinde ürünleri listelemek için kullanılan Adapter sınıfıdır.
 *
 * RecyclerView.Adapter:
 *  - RecyclerView’ın kaç satır göstereceğini
 *  - Her satırın tasarımını
 *  - Satırlara hangi verilerin yerleştirileceğini
 *  kontrol eder.
 *
 * Adapter generic bir yapıdır ve bizden
 * RecyclerView.ViewHolder'dan türeyen bir sınıf ister.
 * Bu projede bu sınıf ProductViewHolder'dır.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context context;
    List<Product> productList;

    // ProductApdater'dan nesne oluşturuken iki tane bilgi yollayacağız
    // Context -> bize mainActivity2 java dosyasını yönetme özelliği sağlıyor
    // List<Product> -> veri tabanından gelen verilerin listesi
    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    // Kartın tasarımı burada hazırlanıyor.

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    // Bu metot, her satır ekrana gelirken çalışır.
    // holder -> o satırın view’ları (TextView, ImageView, Button)
    // position -> listede kaçıncı ürün
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product product = productList.get(position);

        holder.tvName.setText(product.getName());

        holder.tvPrice.setText(product.getPrice() + " TL");

        // Bu satır drawable klasöründeki resmi bulup id değerini verecek bize.
        // çünkü veri tabanında drawable klasöründe ayakkabı isimleri yazıyor bunu string olarak anlamıyor bu
        // id olarak anladığı için biz
        // İlk parametrede getImageName şu isimde örneğin -> Ayakkabı1
        // İkinci parametrede şu drawable dizininde bu isimde fotograf varsa eğer id'sini getir anlamnıa geliyor

        // Yani // Veritabanında "ayakkabi1" yazıyorsa, drawable klasöründeki ayakkabi1.png'yi bulur.
        int resId = context.getResources().getIdentifier(product.getImageName(), "drawable", context.getPackageName());

        // ImageView vardı ya xml'de resim koyduk içine
        holder.imgProduct.setImageResource(resId);

        holder.btnAdd.setOnClickListener(v -> {

            SharedPreferences sp = context.getSharedPreferences("session", Context.MODE_PRIVATE);
            int userId = sp.getInt("user_id", -1);
            // eğer hiçbir kayıtlı id yoksa -1 al dedik sondaki parametrede

            DatabaseHelper db = new DatabaseHelper(context);

            long res = db.addToCart(
                    userId,
                    product.getName(),
                    product.getPrice(),
                    product.getImageName()
            );

            if (res != -1) {
                Toast.makeText(context, product.getName() + " sepete eklendi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Her kartın içinde bulunan view’ları tutar.
    @Override
    public int getItemCount() {
        return productList.size();
    }

    // Dikkat ettiysen yukardaki herşy ProductViewHolder sınıfını kullanarak oluyor

    // ProductViewHolder, RecyclerView’daki tek bir satırın tüm view’larını tutan sınıftır.
    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPrice;
        ImageView imgProduct;
        Button btnAdd;

        // item_product.xml içindeki bileşenler bulunup değişkenlere atanır.
        // Böylece onBindViewHolder içinde hızlıca erişilir.
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            btnAdd = itemView.findViewById(R.id.btnAdd);
        }
    }
}