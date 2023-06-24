package com.example.cookbook.recipeAPI.responses;

import androidx.annotation.NonNull;

import com.example.cookbook.models.Recipe;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * This Class is responsible to hold search Recipe Responses
 **/
public class SearchRecipeResponses {
    @SerializedName("meals")
    @Expose
    private List<Recipe> recipes;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    @NonNull
    @Override
    public String toString() {
        return "SearchRecipeResponses{" +
                "recipes=" + recipes +
                '}';
    }
}
