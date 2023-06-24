package com.example.cookbook.recipeAPI.responses;

import androidx.annotation.NonNull;

import com.example.cookbook.models.Categories;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesResponses {

    @SerializedName("categories")
    @Expose
    private List<Categories> categories;

    public List<Categories> getCategories(){
        return categories;
    }

    @NonNull
    @Override
    public String toString() {
        return "CategoriesResponses{" +
                "categories=" + categories +
                '}';
    }
}
