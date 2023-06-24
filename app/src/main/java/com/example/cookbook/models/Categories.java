package com.example.cookbook.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Categories implements Parcelable {

    @SerializedName("idCategory")
    private final String id;

    @SerializedName("strCategory")
    private final String categoryName;

    @SerializedName("strCategoryThumb")
    private final String image_url;

    @SerializedName("strCategoryDescription")
    private final String categoryDescription;

    protected Categories(Parcel in) {
        id = in.readString();
        categoryName = in.readString();
        image_url = in.readString();
        categoryDescription = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(categoryName);
        dest.writeString(image_url);
        dest.writeString(categoryDescription);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }



    public static final Creator<Categories> CREATOR = new Creator<Categories>() {
        @Override
        public Categories createFromParcel(Parcel in) {
            return new Categories(in);
        }

        @Override
        public Categories[] newArray(int size) {
            return new Categories[size];
        }
    };
}
