package com.example.desafiogfxisrael.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafiogfxisrael.R;
import com.example.desafiogfxisrael.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private final List<Product> products = new ArrayList<>();

    public void updateData(List<Product> newProducts) {
        products.clear();
        if (newProducts != null) {
            products.addAll(newProducts);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView titleTextView;
        private final TextView categoryTextView;
        private final TextView priceTextView;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageProduct);
            titleTextView = itemView.findViewById(R.id.textProductTitle);
            categoryTextView = itemView.findViewById(R.id.textProductCategory);
            priceTextView = itemView.findViewById(R.id.textProductPrice);
        }

        void bind(Product product) {
            titleTextView.setText(product.getTitle());
            categoryTextView.setText(product.getCategory() == null
                    ? itemView.getContext().getString(R.string.filter_all)
                    : product.getCategory().getDisplayName());
            priceTextView.setText(String.format(Locale.US, "$ %.2f", product.getPrice()));
            imageView.setImageResource(R.mipmap.ic_launcher_round);
        }
    }
}
