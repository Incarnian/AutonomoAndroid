package com.cursojava.appautonomo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cursojava.appautonomo.R;
import com.cursojava.appautonomo.model.ProductResponse;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductListItemViewHolder> {

    private List<ProductResponse> products;

    public ProductsAdapter(List<ProductResponse> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productListItem = LayoutInflater
                                .from(parent.getContext())
                                .inflate(R.layout.product_list_item, parent, false);

        return new ProductListItemViewHolder(productListItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListItemViewHolder holder, int position) {
        holder.productName.setText(this.products.get(position).getName());
        holder.productDescription.setText(this.products.get(position).getDescription());
        holder.productPrice.setText(this.products.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return this.products.size();
    }

    public class ProductListItemViewHolder extends RecyclerView.ViewHolder {

        private TextView productName;
        private TextView productDescription;
        private TextView productPrice;

        public ProductListItemViewHolder(@NonNull View productListItem) {
            super(productListItem);

            this.productName = productListItem.findViewById(R.id.product_name);
            this.productDescription = productListItem.findViewById(R.id.product_description);
            this.productPrice = productListItem.findViewById(R.id.product_price);
        }
    }

}
