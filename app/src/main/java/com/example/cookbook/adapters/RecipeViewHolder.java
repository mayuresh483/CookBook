package com.example.cookbook.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbook.R;

public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title, publisher;
    AppCompatImageView image;
    RecipeListener mRecipeListener;

    public RecipeViewHolder(@NonNull View itemView, RecipeListener recipeListener) {
        super(itemView);
        title = itemView.findViewById(R.id.recipe_title);
        publisher = itemView.findViewById(R.id.recipe_publisher);
        image = itemView.findViewById(R.id.recipe_image);
        this.mRecipeListener = recipeListener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.mRecipeListener.onRecipeClick(getAdapterPosition());
    }
}
