package com.example.cookbook.adapters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cookbook.R;
import com.example.cookbook.models.Categories;
import com.example.cookbook.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Recipe> mRecipe;
    private List<Categories> mCategories;
    private RecipeListener mRecipeListener;
    private final static int CATEGORIES_LIST = 0;
    private final static int RECIPE_LIST = 1;
    private final static int LOADING_RECIPE = 2;
    private final static int EXHAUSTED_RECIPE = 3;
    private final static int NO_INTERNET_CONNECTION = 4;

    public RecipeRecyclerViewAdapter(RecipeListener mRecipeListener) {
        this.mRecipeListener = mRecipeListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case CATEGORIES_LIST:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category_list,parent,false);
                return new CategoryViewHolder(view, mRecipeListener);
            }
            case RECIPE_LIST:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recipe_list_item,parent,false);
                return new RecipeViewHolder(view, mRecipeListener);
            }
            case LOADING_RECIPE:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loading_screen,parent,false);
                return new RecipeLoadingView(view);
            }
            case EXHAUSTED_RECIPE:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_exhausted_screen,parent,false);
                return new RecipeExhaustedView(view);
            }
            case NO_INTERNET_CONNECTION:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_noconnection_screen,parent,false);
                return new NoInternetConnectionView(view);
            }
            default:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category_list,parent,false);
                return new CategoryViewHolder(view, mRecipeListener);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemViewType(position);
        if(itemType == RECIPE_LIST) {
            ((RecipeViewHolder) holder).title.setText(mRecipe.get(position).getTitle());
            ((RecipeViewHolder) holder).publisher.setText(mRecipe.get(position).getArea());
            RequestOptions requestOptions = new RequestOptions().
                    placeholder(R.drawable.ic_launcher_background);

            Glide.with(holder.itemView.getContext())
                    .applyDefaultRequestOptions(requestOptions)
                    .load(mRecipe.get(position).getImage_url())
                    .into(((RecipeViewHolder) holder).image);
        }
        else if(itemType == CATEGORIES_LIST){
            ((CategoryViewHolder) holder).categoryName.setText(mCategories.get(position).getCategoryName());

            RequestOptions requestOptions = new RequestOptions().
                    placeholder(R.drawable.ic_launcher_background);
            Glide.with(holder.itemView.getContext())
                    .applyDefaultRequestOptions(requestOptions)
                    .load(mCategories.get(position).getImage_url())
                    .into(((CategoryViewHolder) holder).categoryImage);
        }
    }

    @Override
    public int getItemViewType(int i){
        if(mRecipe==null){
            return CATEGORIES_LIST;
        } else if(mRecipe.get(i).getTitle().equals("Loading...")){
           return LOADING_RECIPE;
        } else if(mRecipe.get(i).getTitle().equals("Exhausted...")){
            return EXHAUSTED_RECIPE;
        } else if(mRecipe.get(i).getTitle().equals("No Internet...")){
            return NO_INTERNET_CONNECTION;
        }else {
           return RECIPE_LIST;
        }
    }

    private boolean isLoading(){
        if(mRecipe!=null){
            if(mRecipe.size()>0){
                if(mRecipe.get(mRecipe.size()-1).getTitle().equals("Loading...")){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isExhausted(){
        if(mRecipe!=null){
            if(mRecipe.size()>0){
                if(mRecipe.get(mRecipe.size()-1).getTitle().equals("Exhausted...")){
                    return true;
                }
            }
        }
        return false;
    }

    public void setLoadingRecipe(){
        if(!isLoading()){
            Recipe recipe = new Recipe();
            recipe.setTitle("Loading...");
            List<Recipe> loadingList = new ArrayList<>();
            loadingList.add(recipe);
            mRecipe = loadingList;
            notifyDataSetChanged();
        }
    }

    public void setExhaustedRecipe(){
        if(!isExhausted()){
            Recipe recipe = new Recipe();
            recipe.setTitle("Exhausted...");
            List<Recipe> exhaustedList = new ArrayList<>();
            exhaustedList.add(recipe);
            mRecipe = exhaustedList;
            notifyDataSetChanged();
        }
    }

    public void setNoInternetConnectionScreen(){
        if(!isConnectionAvailable()){
            Recipe recipe = new Recipe();
            recipe.setTitle("No Internet...");
            List<Recipe> noInternetList = new ArrayList<>();
            noInternetList.add(recipe);
            mRecipe = noInternetList;
            notifyDataSetChanged();
        }
    }

    private boolean isConnectionAvailable(){
        if(mRecipe!=null){
            if(mRecipe.size()>0){
                if(mRecipe.get(mRecipe.size()-1).getTitle().equals("No Internet...")){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getItemCount() {
        if(mRecipe != null){
            return mRecipe.size();
        }
        else if(mCategories!=null){
            return mCategories.size();
        }
        else {
            return 0;
        }
    }

    public void setRecipe(List<Recipe> recipes){
        mRecipe = recipes;
        Log.d("TAG", "setRecipe: "+mRecipe);
        notifyDataSetChanged();
    }

    public List<Recipe> getRecipe(){
        return mRecipe;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCategories(List<Categories> categories){
        mCategories = categories;
        Log.d("TAG", "setCategories: "+mCategories);
        notifyDataSetChanged();
    }

    public List<Categories> getCategories(){
        return mCategories;
    }

    public boolean clearRecipeData(){
        if(mRecipe!=null){
            mRecipe = null;
            notifyDataSetChanged();
            return false;
        } else {
            return true;
        }
    }

    public Recipe getRecipe(int position){
        if(mRecipe!=null){
            if(mRecipe.size() > 0){
                return mRecipe.get(position);
            }
        }
        return null;
    }

}
