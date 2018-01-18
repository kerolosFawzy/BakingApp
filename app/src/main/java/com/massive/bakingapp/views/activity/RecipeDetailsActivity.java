package com.massive.bakingapp.views.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.massive.bakingapp.R;
import com.massive.bakingapp.utlies.Constants;
import com.massive.bakingapp.views.fragment.DetialFragment;
import com.massive.bakingapp.views.fragment.StepDetial;

import io.paperdb.Paper;

public class RecipeDetailsActivity extends AppCompatActivity {
    private static final String TAG_RETAINED_FRAGMENT = "DetialsFragment";
    private static final String TAG_RETAINED_FRAGMENT2 = "stepDetial";
    public static boolean TabletMode = false;
    Fragment DetialsFragment;
    Fragment stepDetial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Paper.init(this);
        FragmentManager manager = getFragmentManager();

        if (findViewById(R.id.RecipeDetailsContainer2) != null)
            TabletMode = true;

        DetialsFragment = manager.findFragmentByTag(TAG_RETAINED_FRAGMENT);
        if (DetialsFragment == null) {
            DetialsFragment = new DetialFragment();
            manager.beginTransaction()
                    .replace(R.id.RecipeDetailsContainer, DetialsFragment, TAG_RETAINED_FRAGMENT)
                    .commit();
        }
        Intent intent = getIntent();
        int id = intent.getIntExtra("StepId", -1);
        stepDetial = manager.findFragmentByTag(TAG_RETAINED_FRAGMENT2);

        if (TabletMode) {
            if (id == -1)
                id=1;
            if (stepDetial == null) {
                stepDetial = new StepDetial();
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.STEP_ID, id);
                stepDetial.setArguments(bundle);
                manager.beginTransaction().replace(R.id.RecipeDetailsContainer2, stepDetial, TAG_RETAINED_FRAGMENT2)
                        .commit();
            }
        } else {

            if (id != -1) {
                if (stepDetial == null) {
                    stepDetial = new StepDetial();
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.STEP_ID, id);
                    stepDetial.setArguments(bundle);
                    manager.beginTransaction().replace(R.id.RecipeDetailsContainer, stepDetial, TAG_RETAINED_FRAGMENT2)
                            .commit();
                }
            }

        }
    }

}
