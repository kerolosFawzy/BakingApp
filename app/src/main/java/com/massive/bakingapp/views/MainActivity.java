package com.massive.bakingapp.views;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.massive.bakingapp.R;


public class MainActivity extends AppCompatActivity {
    private static final String TAG_RETAINED_FRAGMENT = "RetainedFragment";
    Fragment Recipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager=getFragmentManager();
        Recipe=fragmentManager.findFragmentByTag(TAG_RETAINED_FRAGMENT);
        if (Recipe==null){
            Recipe=new CardFragment();
            fragmentManager.beginTransaction().replace(R.id.FragmentContainer,Recipe,TAG_RETAINED_FRAGMENT).commit();
        }
    }
}
