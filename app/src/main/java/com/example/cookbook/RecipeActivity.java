package com.example.cookbook;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cookbook.adapters.RecipeViewHolder;
import com.example.cookbook.models.Recipe;

import java.util.ArrayList;

public class RecipeActivity extends BaseActivity {

    private TextView recipeTitle;
    private AppCompatImageView recipeImage;
    public LinearLayout recipeIngredients, recipeInstruction;
    public ScrollView scrollView;
    private static final String TAG = "RecipeActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        recipeImage = findViewById(R.id.recipe_image);
        recipeTitle = findViewById(R.id.recipe_title);
        recipeInstruction = findViewById(R.id.recipe_instruction_container);
        recipeIngredients = findViewById(R.id.recipe_ingredients_container);
        scrollView = findViewById(R.id.parent);
        showProgressbar(true);
        getRecipeIntent();
    }

    public void getRecipeIntent() {
        if (getIntent().hasExtra("recipe")) {
            Recipe recipe = getIntent().getParcelableExtra("recipe");
            Log.d(TAG, "getRecipeIntent: " + recipe);
            if (recipe != null) {
                setRecipeData(recipe);
            }

        }
    }

    private void setRecipeData(Recipe recipe) {
        recipeTitle.setText(recipe.getTitle());

        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_launcher_background);

        Glide.with(this)
                .applyDefaultRequestOptions(requestOptions)
                .load(recipe.getImage_url())
                .into(recipeImage);

        // Setting Ingredients Data
        recipeIngredients.removeAllViews();

        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add(recipe.getIngredients1());
        ingredients.add(recipe.getIngredients2());
        ingredients.add(recipe.getIngredients3());
        ingredients.add(recipe.getIngredients4());
        ingredients.add(recipe.getIngredients5());
        ingredients.add(recipe.getIngredients6());
        ingredients.add(recipe.getIngredients7());
        ingredients.add(recipe.getIngredients8());
        ingredients.add(recipe.getIngredients9());
        ingredients.add(recipe.getIngredients10());

        ArrayList<String> measures = new ArrayList<>();
        measures.add(recipe.getMeasure1());
        measures.add(recipe.getMeasure2());
        measures.add(recipe.getMeasure3());
        measures.add(recipe.getMeasure4());
        measures.add(recipe.getMeasure5());
        measures.add(recipe.getMeasure6());
        measures.add(recipe.getMeasure7());
        measures.add(recipe.getMeasure8());
        measures.add(recipe.getMeasure9());
        measures.add(recipe.getMeasure10());

        TextView ingredientsView = new TextView(this);

        StringBuilder completeIngredient = new StringBuilder();
        for(int i = 0;i<ingredients.size();i++){
            if(!ingredients.get(i).equals("") && ingredients.get(i)!=null
            && !measures.get(i).equals("") && measures.get(i)!=null){
                completeIngredient.append(measures.get(i)).append(" ").append(ingredients.get(i));
            }
            if(ingredients.size()-1>i){
                completeIngredient.append("\n");
            }
        }

        ingredientsView.setText(completeIngredient.toString().trim());
        ingredientsView.setTextSize(16);
        ingredientsView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        recipeIngredients.addView(ingredientsView);

        // Setting Instruction Data
        recipeInstruction.removeAllViews();

        TextView instructions = new TextView(this);
        instructions.setText(recipe.getInstructions());
        instructions.setTextSize(16);
        instructions.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        recipeInstruction.addView(instructions);

        showProgressbar(false);
    }

}
