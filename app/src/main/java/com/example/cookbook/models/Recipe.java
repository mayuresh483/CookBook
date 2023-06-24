package com.example.cookbook.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Recipe implements Parcelable{
    @SerializedName("strMeal")
    private String title;

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                ", area='" + area + '\'' +
                ", ingredients1='" + ingredients1 + '\'' +
                ", ingredients2='" + ingredients2 + '\'' +
                ", ingredients3='" + ingredients3 + '\'' +
                ", ingredients4='" + ingredients4 + '\'' +
                ", ingredients5='" + ingredients5 + '\'' +
                ", ingredients6='" + ingredients6 + '\'' +
                ", ingredients7='" + ingredients7 + '\'' +
                ", ingredients8='" + ingredients8 + '\'' +
                ", ingredients9='" + ingredients9 + '\'' +
                ", ingredients10='" + ingredients10 + '\'' +
                ", measure1='" + measure1 + '\'' +
                ", measure2='" + measure2 + '\'' +
                ", measure3='" + measure3 + '\'' +
                ", measure4='" + measure4 + '\'' +
                ", measure5='" + measure5 + '\'' +
                ", measure6='" + measure6 + '\'' +
                ", measure7='" + measure7 + '\'' +
                ", measure8='" + measure8 + '\'' +
                ", measure9='" + measure9 + '\'' +
                ", measure10='" + measure10 + '\'' +
                ", recipe_id='" + recipe_id + '\'' +
                ", instructions='" + instructions + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }

    @SerializedName("strArea")
    private String area;

    protected Recipe(Parcel in) {
        title = in.readString();
        area = in.readString();
        ingredients1 = in.readString();
        ingredients2 = in.readString();
        ingredients3 = in.readString();
        ingredients4 = in.readString();
        ingredients5 = in.readString();
        ingredients6 = in.readString();
        ingredients7 = in.readString();
        ingredients8 = in.readString();
        ingredients9 = in.readString();
        ingredients10 = in.readString();
        measure1 = in.readString();
        measure2 = in.readString();
        measure3 = in.readString();
        measure4 = in.readString();
        measure5 = in.readString();
        measure6 = in.readString();
        measure7 = in.readString();
        measure8 = in.readString();
        measure9 = in.readString();
        measure10 = in.readString();
        recipe_id = in.readString();
        instructions = in.readString();
        image_url = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public String getIngredients1() {
        return ingredients1;
    }

    public void setIngredients1(String ingredients1) {
        this.ingredients1 = ingredients1;
    }

    public String getIngredients2() {
        return ingredients2;
    }

    public void setIngredients2(String ingredients2) {
        this.ingredients2 = ingredients2;
    }

    public String getIngredients3() {
        return ingredients3;
    }

    public void setIngredients3(String ingredients3) {
        this.ingredients3 = ingredients3;
    }

    public String getIngredients4() {
        return ingredients4;
    }

    public void setIngredients4(String ingredients4) {
        this.ingredients4 = ingredients4;
    }

    public String getIngredients5() {
        return ingredients5;
    }

    public void setIngredients5(String ingredients5) {
        this.ingredients5 = ingredients5;
    }

    public String getIngredients6() {
        return ingredients6;
    }

    public void setIngredients6(String ingredients6) {
        this.ingredients6 = ingredients6;
    }

    public String getIngredients7() {
        return ingredients7;
    }

    public void setIngredients7(String ingredients7) {
        this.ingredients7 = ingredients7;
    }

    public String getIngredients8() {
        return ingredients8;
    }

    public void setIngredients8(String ingredients8) {
        this.ingredients8 = ingredients8;
    }

    public String getIngredients9() {
        return ingredients9;
    }

    public void setIngredients9(String ingredients9) {
        this.ingredients9 = ingredients9;
    }

    public String getIngredients10() {
        return ingredients10;
    }

    public void setIngredients10(String ingredients10) {
        this.ingredients10 = ingredients10;
    }

    public String getMeasure1() {
        return measure1;
    }

    public void setMeasure1(String measure1) {
        this.measure1 = measure1;
    }

    public String getMeasure2() {
        return measure2;
    }

    public void setMeasure2(String measure2) {
        this.measure2 = measure2;
    }

    public String getMeasure3() {
        return measure3;
    }

    public void setMeasure3(String measure3) {
        this.measure3 = measure3;
    }

    public String getMeasure4() {
        return measure4;
    }

    public void setMeasure4(String measure4) {
        this.measure4 = measure4;
    }

    public String getMeasure5() {
        return measure5;
    }

    public void setMeasure5(String measure5) {
        this.measure5 = measure5;
    }

    public String getMeasure6() {
        return measure6;
    }

    public void setMeasure6(String measure6) {
        this.measure6 = measure6;
    }

    public String getMeasure7() {
        return measure7;
    }

    public void setMeasure7(String measure7) {
        this.measure7 = measure7;
    }

    public String getMeasure8() {
        return measure8;
    }

    public void setMeasure8(String measure8) {
        this.measure8 = measure8;
    }

    public String getMeasure9() {
        return measure9;
    }

    public void setMeasure9(String measure9) {
        this.measure9 = measure9;
    }

    public String getMeasure10() {
        return measure10;
    }

    public void setMeasure10(String measure10) {
        this.measure10 = measure10;
    }

    @SerializedName("strIngredient1")
    private String ingredients1;
    @SerializedName("strIngredient2")
    private String ingredients2;
    @SerializedName("strIngredient3")
    private String ingredients3;
    @SerializedName("strIngredient4")
    private String ingredients4;
    @SerializedName("strIngredient5")
    private String ingredients5;
    @SerializedName("strIngredient6")
    private String ingredients6;
    @SerializedName("strIngredient7")
    private String ingredients7;
    @SerializedName("strIngredient8")
    private String ingredients8;
    @SerializedName("strIngredient9")
    private String ingredients9;
    @SerializedName("strIngredient10")
    private String ingredients10;


    @SerializedName("strMeasure1")
    private String measure1;
    @SerializedName("strMeasure2")
    private String measure2;
    @SerializedName("strMeasure3")
    private String measure3;
    @SerializedName("strMeasure4")
    private String measure4;
    @SerializedName("strMeasure5")
    private String measure5;
    @SerializedName("strMeasure6")
    private String measure6;
    @SerializedName("strMeasure7")
    private String measure7;
    @SerializedName("strMeasure8")
    private String measure8;
    @SerializedName("strMeasure9")
    private String measure9;
    @SerializedName("strMeasure10")
    private String measure10;

    @SerializedName("idMeal")
    private String recipe_id;

    @SerializedName("strInstructions")
    private String instructions;
    @SerializedName("strMealThumb")
    private String image_url;

    public Recipe() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArea() {
        return area;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getInstructions() {
        return instructions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(area);
        dest.writeString(ingredients1);
        dest.writeString(ingredients2);
        dest.writeString(ingredients3);
        dest.writeString(ingredients4);
        dest.writeString(ingredients5);
        dest.writeString(ingredients6);
        dest.writeString(ingredients7);
        dest.writeString(ingredients8);
        dest.writeString(ingredients9);
        dest.writeString(ingredients10);
        dest.writeString(measure1);
        dest.writeString(measure2);
        dest.writeString(measure3);
        dest.writeString(measure4);
        dest.writeString(measure5);
        dest.writeString(measure6);
        dest.writeString(measure7);
        dest.writeString(measure8);
        dest.writeString(measure9);
        dest.writeString(measure10);
        dest.writeString(recipe_id);
        dest.writeString(instructions);
        dest.writeString(image_url);
    }
}
