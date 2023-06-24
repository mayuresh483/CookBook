package com.example.cookbook.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.cookbook.models.Categories;
import com.example.cookbook.models.Recipe;
import com.example.cookbook.repositories.RecipeRepository;

import java.util.List;

public class RecipeListViewModel extends ViewModel {
    private RecipeRepository mRecipeRepository;

    public RecipeListViewModel() {
        mRecipeRepository = RecipeRepository.getInstance();
    }

    public LiveData<List<Recipe>> getRecipes(){
        return mRecipeRepository.getRecipes();
    }

    public void searchRecipes(String query,Boolean isSubmitted){
        mRecipeRepository.searchRecipes(query, isSubmitted);
    }

    public LiveData<List<Categories>> getCategories(){
        return mRecipeRepository.getCategories();
    }

    public LiveData<Boolean> getRecipeResponseTimeOutState(){
        return mRecipeRepository.getRecipeResponseTimeOutState();
    }

    public LiveData<Boolean> isQueryExhausted(){
        return mRecipeRepository.isQueryExhausted();
    }
}
