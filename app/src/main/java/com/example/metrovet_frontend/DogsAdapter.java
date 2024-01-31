// DogsAdapter.java
package com.example.metrovet_frontend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DogsAdapter extends RecyclerView.Adapter<DogsAdapter.DogViewHolder> {

    private List<String> dogsList;

    public DogsAdapter(List<String> dogsList) {
        this.dogsList = dogsList;
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dog, parent, false);
        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        String dogName = dogsList.get(position);
        holder.bind(dogName);
    }

    @Override
    public int getItemCount() {
        return dogsList.size();
    }

    static class DogViewHolder extends RecyclerView.ViewHolder {

        private final TextView dogNameTextView;

        public DogViewHolder(@NonNull View itemView) {
            super(itemView);
            dogNameTextView = itemView.findViewById(R.id.item_dog_name);
        }

        public void bind(String dogName) {
            dogNameTextView.setText(dogName);
        }
    }
}
