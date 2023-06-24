package com.example.cookbook.recipeAPI;

import com.example.cookbook.recipeAPI.responses.CategoriesResponses;
import com.example.cookbook.recipeAPI.responses.RecipeResponses;
import com.example.cookbook.recipeAPI.responses.SearchRecipeResponses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRecipeAPI {
    // Search Recipe by name
    @GET("api/json/v1/1/search.php")
    Call<SearchRecipeResponses> getSpecificRecipe(
            @Query("s") String s
    );

    // Search & List all Recipe by first letter
    @GET("api/json/v1/1/search.php")
    Call<SearchRecipeResponses> getRecipeCharStartsWith(
            @Query("f") String f
    );

    // Get Recipe With respect to ID
    @GET("api/json/v1/1/lookup.php")
    Call<RecipeResponses> getRecipe(
            @Query("i") String id
    );

    // Get all Categories
    @GET("api/json/v1/1/categories.php")
    Call<CategoriesResponses> getCategories();

}
