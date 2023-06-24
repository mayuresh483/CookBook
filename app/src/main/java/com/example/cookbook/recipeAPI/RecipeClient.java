package com.example.cookbook.recipeAPI;

import static com.example.cookbook.utils.Constants.NETWORK_TIMEOUT;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cookbook.NetworkExecutor;
import com.example.cookbook.models.Categories;
import com.example.cookbook.models.Recipe;
import com.example.cookbook.recipeAPI.responses.CategoriesResponses;
import com.example.cookbook.recipeAPI.responses.SearchRecipeResponses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class RecipeClient {
    private static RecipeClient instance;
    private MutableLiveData<List<Recipe>> mRecipes;
    private MutableLiveData<List<Categories>> mCategories;
    private MutableLiveData<Boolean> mRecipeResponseTimeOut = new MutableLiveData<>();
    private RecipeClassRunnable mRecipeClassRunnable;
    private CategoriesClassRunnable mCategoriesClassRunnable;

    private static final String TAG = "RecipeClient";

    public static RecipeClient getInstance(){
        if(instance == null){
            instance = new RecipeClient();
        }
        return instance;
    }

    public RecipeClient(){
        mRecipes = new MutableLiveData<>();
        mCategories = new MutableLiveData<>();
        mRecipeResponseTimeOut.postValue(false);
    }

    public LiveData<List<Recipe>> getRecipe(){
        return mRecipes;
    }

    public LiveData<Boolean> getRecipeResponseTimeOutState(){
        return mRecipeResponseTimeOut;
    }

    public LiveData<List<Categories>> getCategories(){
        getCategorie();
        return mCategories;
    }

    public void searchRecipes(String query, Boolean isSubmitted){
        if(mRecipeClassRunnable!=null){
            mRecipeClassRunnable = null;
        }
        mRecipeClassRunnable = new RecipeClassRunnable(query,isSubmitted);

        final Future handler = NetworkExecutor.getInstance().
                getNetworkExecutor().submit(mRecipeClassRunnable);

        // Handle API Timeout
        NetworkExecutor.getInstance().getNetworkExecutor().schedule(new Runnable() {
            @Override
            public void run() {
                //stop the request
                mRecipeResponseTimeOut.postValue(true);
                handler.cancel(true);
            }
        },NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    public void getCategorie(){
        mCategoriesClassRunnable = new CategoriesClassRunnable();

        final Future handler = NetworkExecutor.getInstance().
                getNetworkExecutor().submit(mCategoriesClassRunnable);

        // Handle API Timeout
        NetworkExecutor.getInstance().getNetworkExecutor().schedule(new Runnable() {
            @Override
            public void run() {
                //stop the request
                mRecipeResponseTimeOut.postValue(true);
                handler.cancel(true);
            }
        },NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    public class RecipeClassRunnable implements Runnable{
        private String query;
        private Boolean isSubmitted;
        private Boolean cancelled;

        public RecipeClassRunnable(String query, Boolean isSubmitted) {
            this.query = query;
            this.isSubmitted = isSubmitted;
            cancelled = false;
        }

        @Override
        public void run() {
            Response response;
            try {
                if(isSubmitted){
                    response = getRecipes().execute();
                } else{
                    response = getRecipeCharStartsWith().execute();
                }

                if(cancelled){
                    return;
                }
                if(response.isSuccessful()){
                    if(((SearchRecipeResponses)response.body()).getRecipes()!=null){
                        List<Recipe> recipes = new ArrayList<>(((SearchRecipeResponses)response.body()).
                                getRecipes());
                        mRecipes.postValue(recipes);
                    } else {
                        mRecipes.postValue(null);
                    }

                }else{
                    mRecipes.postValue(null);
                }
            } catch (IOException e) {
                Log.d(TAG, "Exception "+e.getMessage());
                mRecipes.postValue(null);
                mRecipeResponseTimeOut.postValue(true);
                throw new RuntimeException(e);
            }
        }

        public Call<SearchRecipeResponses> getRecipes(){
            return ServiceGenerator.getRecipeAPI().getSpecificRecipe(query);
        }

        public Call<SearchRecipeResponses> getRecipeCharStartsWith(){
            return  ServiceGenerator.getRecipeAPI().getRecipeCharStartsWith(query);
        }

        public void cancelRequest(){
            Log.d(TAG, "cancelRequest: ");
            cancelled = true;
        }
    }

    public class CategoriesClassRunnable implements Runnable{
        private Boolean cancelled;

        public CategoriesClassRunnable() {
            cancelled = false;
        }

        @Override
        public void run() {
            Response response;
            try {
                response = getCategories().execute();
                if(cancelled){
                    return;
                }
                if(response.isSuccessful()){
                    List<Categories> categories = new ArrayList<>(((CategoriesResponses)response.body()).
                            getCategories());
                    mCategories.postValue(categories);
                    Log.d(TAG, "run: "+categories);
                }else{
                    mCategories.postValue(null);
                }
            } catch (IOException e) {
                Log.d(TAG, "Exception "+e.getMessage());
                mCategories.postValue(null);
                throw new RuntimeException(e);
            }
        }

        public Call<CategoriesResponses> getCategories(){
            return  ServiceGenerator.getRecipeAPI().getCategories();
        }

        public void cancelRequest(){
            Log.d(TAG, "cancelRequest: ");
            cancelled = true;
        }
    }
}
