package com.example.cookbook.recipeAPI.responses;

import com.example.cookbook.models.Recipe;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
* This Class is responsible to hold single Recipe Responses
 **/
public class RecipeResponses {
    @SerializedName("meals")
    @Expose
    private List<Recipe> recipe;

    public List<Recipe> getRecipes() {
        return recipe;
    }

    @Override
    public String toString() {
        return "RecipeResponses{" +
                "recipe='" + recipe + '\'' +
                '}';
    }
}
