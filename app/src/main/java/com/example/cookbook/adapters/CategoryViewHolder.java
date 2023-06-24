package com.example.cookbook.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbook.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    CircleImageView categoryImage;
    TextView categoryName;
    RecipeListener recipeListener;

    public CategoryViewHolder(@NonNull View itemView, RecipeListener recipeListener) {
        super(itemView);

        this.recipeListener = recipeListener;

        categoryImage = itemView.findViewById(R.id.category_image);
        categoryName = itemView.findViewById(R.id.category_name);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.recipeListener.onCategoryClick(categoryName.getText().toString());
    }
}
