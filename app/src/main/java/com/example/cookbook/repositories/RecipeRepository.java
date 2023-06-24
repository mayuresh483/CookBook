package com.example.cookbook.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.cookbook.models.Categories;
import com.example.cookbook.models.Recipe;
import com.example.cookbook.recipeAPI.RecipeClient;

import java.util.List;

public class RecipeRepository {
    private static RecipeRepository instance;
    private RecipeClient mRecipeClient;
    private MutableLiveData<Boolean> isRecipeDataExhausted = new MutableLiveData<>();
    private MediatorLiveData<List<Recipe>> mRecipe;

    public static RecipeRepository getInstance() {
        if(instance == null){
            instance = new RecipeRepository();
        }
        return instance;
    }
    public RecipeRepository(){
        mRecipeClient = RecipeClient.getInstance();
        mRecipe = new MediatorLiveData<>();
        getMediatorRecipeData();
    }

    public void getMediatorRecipeData(){
        mRecipe.addSource(mRecipeClient.getRecipe(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                if(recipes!=null) {
                    mRecipe.setValue(recipes);
                    isRecipeDataExhausted.postValue(false);
                }else{
                    setQueryExhausted();
                }
            }
        });
    }

    private void setQueryExhausted(){
        isRecipeDataExhausted.setValue(true);
    }

    public LiveData<List<Recipe>> getRecipes(){
        return mRecipe;
    }

    public void searchRecipes(String query, Boolean isSubmitted){
        mRecipeClient.searchRecipes(query, isSubmitted);
    }

    public LiveData<List<Categories>> getCategories(){
        return mRecipeClient.getCategories();
    }

    public LiveData<Boolean> isQueryExhausted(){
        return isRecipeDataExhausted;
    }

    public LiveData<Boolean> getRecipeResponseTimeOutState(){
        return mRecipeClient.getRecipeResponseTimeOutState();
    }
}
