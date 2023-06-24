package com.example.cookbook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbook.adapters.RecipeListener;
import com.example.cookbook.adapters.RecipeRecyclerViewAdapter;
import com.example.cookbook.models.Categories;
import com.example.cookbook.viewmodels.RecipeListViewModel;
import com.example.cookbook.models.Recipe;

import java.util.List;

public class RecipeListActivity extends BaseActivity implements RecipeListener {

    private static final String TAG = "RecipeListActivity";
    private RecipeListViewModel mRecipeListViewModel;
    private RecyclerView recyclerView;
    private RecipeRecyclerViewAdapter adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

        mRecipeListViewModel = new ViewModelProvider(this).get(RecipeListViewModel.class);

        recyclerView = findViewById(R.id.recipe_recyclerview);
        adapter = new RecipeRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchView = findViewById(R.id.search_bar);
        subscribeObservers();
        SearchRecipe();
        getCategories();
    }

    void subscribeObservers(){
        mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                if(recipes != null){
                    Log.d(TAG, "onChanged: "+recipes);
                    adapter.setRecipe(recipes);
                }
            }
        });

        mRecipeListViewModel.getCategories().observe(this, new Observer<List<Categories>>() {
            @Override
            public void onChanged(List<Categories> categories) {
                if(categories != null){
                    Log.d(TAG, "onChanged: "+categories);
                    adapter.setCategories(categories);
                }
            }
        });

        mRecipeListViewModel.getRecipeResponseTimeOutState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean && adapter.getRecipe()==null && adapter.getCategories()==null){
                    Log.d(TAG, "onChanged: timeout");
                    adapter.setNoInternetConnectionScreen();
                }
            }
        });

        mRecipeListViewModel.isQueryExhausted().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Log.d(TAG, "onChanged: IsQueryExhausted");
                    adapter.setExhaustedRecipe();
                }
            }
        });
    }

    private void SearchRecipe(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.setLoadingRecipe();
                mRecipeListViewModel.searchRecipes(query,true);
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                adapter.setLoadingRecipe();
//                mRecipeListViewModel.searchRecipes(newText,false);
                return false;
            }
        });
    }

    public void getCategories(){
        mRecipeListViewModel.getCategories();
    }

    @Override
    public void onRecipeClick(int position) {
        Intent intent = new Intent(this,RecipeActivity.class);
        intent.putExtra("recipe",adapter.getRecipe(position));
        startActivity(intent);
    }

    @Override
    public void onCategoryClick(String category) {
        searchView.setQuery("", false);
        searchView.setIconified(true);
        adapter.setLoadingRecipe();
        mRecipeListViewModel.searchRecipes(category,true);
    }

    @Override
    public void onBackPressed() {
        searchView.setQuery("", false);
        searchView.setIconified(true);
        if(adapter.clearRecipeData()){
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recipe_menu_list,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.recipe_category_menu){
            adapter.clearRecipeData();
        }
        return super.onOptionsItemSelected(item);
    }
}