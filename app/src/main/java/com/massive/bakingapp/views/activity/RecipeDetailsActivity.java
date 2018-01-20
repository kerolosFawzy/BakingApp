package com.massive.bakingapp.views.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

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
    int id;

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(Constants.STEP_ID, id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        try {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
        }
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
        id = intent.getIntExtra(Constants.STEP_ID, -1);
        stepDetial = manager.findFragmentByTag(TAG_RETAINED_FRAGMENT2);
        if (stepDetial == null) {
            if (savedInstanceState != null && id == -1) {
                id = savedInstanceState.getInt(Constants.STEP_ID);
            }
        }
        
        if (TabletMode) {
            if (id == -1)
                id = 1;
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
                stepDetial = new StepDetial();
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.STEP_ID, id);
                stepDetial.setArguments(bundle);
                manager.beginTransaction().replace(R.id.RecipeDetailsContainer, stepDetial, TAG_RETAINED_FRAGMENT2)
                        .commit();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
