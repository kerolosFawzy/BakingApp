package com.massive.bakingapp.interfaces;

import com.massive.bakingapp.models.Ingredients;
import com.massive.bakingapp.models.Steps;

import java.util.ArrayList;

/**
 * Created by minafaw on 11/22/2017.
 */

public interface CallBack {
    void getData(ArrayList<Ingredients> ingredients,ArrayList<Steps> steps);
}
