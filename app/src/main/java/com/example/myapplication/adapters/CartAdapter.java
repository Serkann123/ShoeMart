package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.models.CartItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartVH> {

    public interface OnCartChangedListener {
        void onCartChanged();
    }

    Context context;
    List<CartItem> list;
    OnCartChangedListener listener;

    public CartAdapter(Context context, List<CartItem> list, OnCartChangedListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartVH holder, int position) {
        CartItem item = list.get(position);
        holder.tvName.setText(item.getUrunAdi());
        holder.tvPrice.setText(item.getFiyat() + " TL");

        int resId = context.getResources().getIdentifier(item.getResim(), "drawable", context.getPackageName());
        holder.img.setImageResource(resId);

        holder.imgDelete.setOnClickListener(v -> {
            DatabaseHelper db = new DatabaseHelper(context);
            int sepetId = item.getSepetId();

            db.deleteCartItem(sepetId);

            int pos = holder.getAdapterPosition(); // ✅ position yerine bu daha güvenli
            if (pos != RecyclerView.NO_POSITION) {
                list.remove(pos);
                notifyItemRemoved(pos);
                notifyItemRangeChanged(pos, list.size());
            }

            Toast.makeText(context, "Ürün sepetten silindi", Toast.LENGTH_SHORT).show();

            if (listener != null) listener.onCartChanged(); // ✅ toplamı güncelle
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class CartVH extends RecyclerView.ViewHolder {
        ImageView img, imgDelete;
        TextView tvName, tvPrice;

        public CartVH(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgCart);
            tvName = itemView.findViewById(R.id.tvCartName);
            tvPrice = itemView.findViewById(R.id.tvCartPrice);
            imgDelete = itemView.findViewById(R.id.imgDelete);
        }
    }
}
