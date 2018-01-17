package com.massive.bakingapp.views.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.massive.bakingapp.R;
import com.massive.bakingapp.adapters.IngredientAdapter;
import com.massive.bakingapp.adapters.stepsAdapter;
import com.massive.bakingapp.interfaces.CallBack;
import com.massive.bakingapp.models.Ingredients;
import com.massive.bakingapp.models.Steps;
import com.massive.bakingapp.network.NetworkCheck;
import com.massive.bakingapp.utlies.Constants;
import com.massive.bakingapp.utlies.Utlies;
import com.massive.bakingapp.views.activity.RecipeDetailsActivity;

import java.util.ArrayList;

import io.paperdb.Paper;


public class DetialFragment extends Fragment {
    RecyclerView IngreRecyclerView;
    RecyclerView StepsRecyclerView;
    RecyclerView.LayoutManager IngrelayoutManager;
    RecyclerView.LayoutManager SteplayoutManager;
    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.details_fragment, container, false);
        Button button = rootView.findViewById(R.id.AddWidget);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Paper.book().contains(Constants.INGREDIENT_PAPER)) {
                    Paper.book().delete(Constants.INGREDIENT_PAPER);
                    Paper.book().write(Constants.INGREDIENT_PAPER, CardFragment.ingredients);
                } else
                    Paper.book().write(Constants.INGREDIENT_PAPER, CardFragment.ingredients);
            }
        });
        showDetials();
        return rootView;
    }

    private void showDetials() {
        if (NetworkCheck.isNetworkAvailable(getActivity())) {
            IngreRecyclerView = rootView.findViewById(R.id.IngredientsRecyclerView);
            IngrelayoutManager = new LinearLayoutManager(getActivity());
            IngreRecyclerView.setLayoutManager(IngrelayoutManager);
            StepsRecyclerView = rootView.findViewById(R.id.stepsRecyclerView);
            SteplayoutManager = new LinearLayoutManager(getActivity());
            StepsRecyclerView.setLayoutManager(SteplayoutManager);
            try {
                RecyclerView.Adapter adapter = new IngredientAdapter(CardFragment.ingredients);
                IngreRecyclerView.setAdapter(adapter);
                RecyclerView.Adapter adapter1 = new stepsAdapter(CardFragment.steps, getActivity(), new CallBack() {
                    @Override
                    public void getData(ArrayList<Ingredients> ingredients, ArrayList<Steps> steps) {

                    }

                    @Override
                    public void getId(int id) {
                        Intent intent = new Intent(getActivity(), RecipeDetailsActivity.class);
                        intent.putExtra(Constants.STEP_ID, id);
                        startActivity(intent);
                    }
                });
                StepsRecyclerView.setAdapter(adapter1);
            } catch (NullPointerException e) {
                Log.e("null", "no ingredients found ");
            }

//            if (StepsRecyclerView.getParent() != null)
//                ((ViewGroup) StepsRecyclerView.getParent()).removeView(StepsRecyclerView);

        } else
            Utlies.showErrormessage(getActivity());
    }
}
