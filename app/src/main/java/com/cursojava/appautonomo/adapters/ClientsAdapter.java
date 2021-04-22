package com.cursojava.appautonomo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cursojava.appautonomo.R;
import com.cursojava.appautonomo.model.ClientResponse;

import java.util.List;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ClientsListViewHolder> {

    private List<ClientResponse> clients;

    public ClientsAdapter(List<ClientResponse> clients) {
        this.clients = clients;
    }

    @NonNull
    @Override
    public ClientsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.client_list_item, parent, false);
        return new ClientsListViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientsListViewHolder holder, int position) {
        //Verificar
        holder.nome.setText(this.clients.get(position).getName());
        holder.telefone.setText(this.clients.get(position).getPhone());
        holder.cpf.setText(this.clients.get(position).getCpf());
    }


    @Override
    public int getItemCount() {
        return this.clients.size();
    }

    public class ClientsListViewHolder extends RecyclerView.ViewHolder {

        TextView nome;
        TextView telefone;
        TextView cpf;
        //ImageView foto;

        public ClientsListViewHolder(@NonNull View clientListItem) {
            super(clientListItem);
            //Verificar
            this.nome = clientListItem.findViewById(R.id.client_name);
            this.telefone = clientListItem.findViewById(R.id.client_phone);
            this.cpf = clientListItem.findViewById(R.id.client_id);

        }
    }

}
