// DogsAdapter.java
package com.example.metrovet_frontend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.metrovet_frontend.model.Dog;

import java.util.List;

public class DogsAdapter extends RecyclerView.Adapter<DogsAdapter.DogViewHolder> {

    private List<Dog> dogsList;
    private OnItemClickListener onItemClickListener;

    public DogsAdapter(List<Dog> dogsList) {
        this.dogsList = dogsList;
    }

    public void setDogsList(List<Dog> dogsList) {
        this.dogsList = dogsList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dog, parent, false);
        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        Dog dog = dogsList.get(position);
        holder.bind(dog);

        // Set click listener for the item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(dog);
                }
            }
        });

        // Set click listener for the edit_button
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onEditButtonClick(dog);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dogsList.size();
    }

    static class DogViewHolder extends RecyclerView.ViewHolder {

        private final TextView dogNameTextView;
        private final ImageView editButton;

        public DogViewHolder(@NonNull View itemView) {
            super(itemView);
            dogNameTextView = itemView.findViewById(R.id.item_dog_name);
            editButton = itemView.findViewById(R.id.edit_button);
        }

        public void bind(Dog dog) {
            dogNameTextView.setText(dog.getDogName());
        }
    }

    // Interface for item click listener
    public interface OnItemClickListener {
        void onItemClick(Dog dog);

        void onEditButtonClick(Dog dog);
    }
}
