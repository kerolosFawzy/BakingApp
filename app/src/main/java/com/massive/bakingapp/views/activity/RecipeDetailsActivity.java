package com.massive.bakingapp.views.activity;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.massive.bakingapp.R;
import com.massive.bakingapp.views.fragment.DetialFragment;

public class RecipeDetailsActivity extends AppCompatActivity {
    private static final String TAG_RETAINED_FRAGMENT = "DetialsFragment";
    Fragment DetialsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        FragmentManager manager = getFragmentManager();
        DetialsFragment = manager.findFragmentByTag(TAG_RETAINED_FRAGMENT);
        if (DetialsFragment == null) {
            DetialsFragment = new DetialFragment();
            manager.beginTransaction()
                    .replace(R.id.RecipeDetailsContainer, DetialsFragment, TAG_RETAINED_FRAGMENT)
                    .commit();
        }
    }
}
