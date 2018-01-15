package com.massive.bakingapp.views.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.massive.bakingapp.R;
import com.massive.bakingapp.views.fragment.CardFragment;


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


////ToDo need widget  1
////ToDo need database to store wediget data 2
////ToDo need tablet handling 3
////ToDo need hand view problems 4