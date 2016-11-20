package com.example.loganpatino.hackohio2016;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by loganpatino on 11/20/16.
 */

public class RetrofitCategoryInterface {
    public interface CategoryService {
        @GET("getcategories.php")
        Call<List<Category>> getCategories();

        @GET("getactivitycategories.php")
        Call<List<Category>> getActivityCategories();
    }
}
