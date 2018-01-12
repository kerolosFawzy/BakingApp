package com.massive.bakingapp.views.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.massive.bakingapp.R;
import com.massive.bakingapp.views.fragment.DetialFragment;
import com.massive.bakingapp.views.fragment.StepDetial;

public class RecipeDetailsActivity extends AppCompatActivity {
    private static final String TAG_RETAINED_FRAGMENT = "DetialsFragment";
    private static final String TAG_RETAINED_FRAGMENT2 = "stepDetial";
    Fragment DetialsFragment;
    Fragment stepDetial;

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

        Intent intent = getIntent();
        int id = intent.getIntExtra("StepId", -1);
        if (id != -1) {
            stepDetial = manager.findFragmentByTag(TAG_RETAINED_FRAGMENT2);
            if (stepDetial == null) {
                stepDetial = new StepDetial();
                Bundle bundle=new Bundle();
                bundle.putInt("StepId",id);
                stepDetial.setArguments(bundle);
                manager.beginTransaction().replace(R.id.RecipeDetailsContainer, stepDetial, TAG_RETAINED_FRAGMENT2)
                        .commit();
            }
        }

    }
}
