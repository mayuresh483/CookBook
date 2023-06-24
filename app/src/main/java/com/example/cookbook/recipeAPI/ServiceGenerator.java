package com.example.cookbook.recipeAPI;

import com.example.cookbook.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static IRecipeAPI recipeAPI = retrofit.create(IRecipeAPI.class);

    public static IRecipeAPI getRecipeAPI() {
        return recipeAPI;
    }
}
