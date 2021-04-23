package com.cursojava.appautonomo.adapters;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cursojava.appautonomo.R;
import com.cursojava.appautonomo.model.SupplierResponse;

import java.util.List;

public class SuppliersAdapter extends RecyclerView.Adapter<SuppliersAdapter.SupplierListItemViewHolder> {

    private List<SupplierResponse> suppliers;

    public SuppliersAdapter( List<SupplierResponse> suppliers){
        this.suppliers = suppliers;
    }
    @NonNull
    @Override
    public SupplierListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View supplierListItem = LayoutInflater
                                .from(parent.getContext())
                                .inflate(R.layout.supplier_list_item, parent, false);

        return new SupplierListItemViewHolder(supplierListItem);
    }

    @Override
    public void onBindViewHolder (@NonNull SupplierListItemViewHolder holder, int position) {
        holder.supplierName.setText(this.suppliers.get(position).getName());
        holder.supplierCNPJ.setText(this.suppliers.get(position).getCnpj());
        holder.supplierID.setText(suppliers.get(position).getId().toString());
    }

    @Override
    public int getItemCount() {
        return this.suppliers.size();
    }

    public class SupplierListItemViewHolder extends RecyclerView.ViewHolder {

        private TextView supplierName;
        private TextView supplierCNPJ;
        private TextView supplierID;

        public SupplierListItemViewHolder(@NonNull View supplierListItem) {
            super(supplierListItem);

            this.supplierName = supplierListItem.findViewById(R.id.supplier_name);
            this.supplierCNPJ = supplierListItem.findViewById(R.id.supplier_cnpj);
            this.supplierID = supplierListItem.findViewById(R.id.supplier_id);

        }
    }
}
