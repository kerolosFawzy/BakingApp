package com.massive.bakingapp.network;

import com.massive.bakingapp.models.Recipe;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by minafaw on 11/21/2017.
 */

public interface RetroApiInterface {
    @GET("topher/2017/May/59121517_baking/baking")
    Call<Recipe> GetRecipe();
}
