package com.massive.bakingapp.views.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.massive.bakingapp.R;
import com.massive.bakingapp.adapters.IngredientAdapter;
import com.massive.bakingapp.adapters.stepsAdapter;
import com.massive.bakingapp.network.NetworkCheck;
import com.massive.bakingapp.utlies.Utlies;


public class DetialFragment extends Fragment {
    RecyclerView IngreRecyclerView;
    RecyclerView StepsRecyclerView;
    RecyclerView.LayoutManager IngrelayoutManager;
    RecyclerView.LayoutManager SteplayoutManager;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.details_fragment, container, true);
        showDetials();
        return view;
    }

    private void showDetials() {
        if (NetworkCheck.isNetworkAvailable(getActivity())) {
            IngreRecyclerView = view.findViewById(R.id.IngredientsRecyclerView);
            IngrelayoutManager = new LinearLayoutManager(getActivity());
            IngreRecyclerView.setLayoutManager(IngrelayoutManager);

            try {
                RecyclerView.Adapter adapter = new IngredientAdapter(CardFragment.ingredients);
                IngreRecyclerView.setAdapter(adapter);
            } catch (NullPointerException e) {
                Log.e("null", "no ingredients found ");
            }


            StepsRecyclerView = view.findViewById(R.id.stepsRecyclerView);
            SteplayoutManager = new LinearLayoutManager(getActivity());
            StepsRecyclerView.setLayoutManager(SteplayoutManager);
            RecyclerView.Adapter adapter1 = new stepsAdapter(CardFragment.steps);
            StepsRecyclerView.setAdapter(adapter1);

//            try {
//                StepsRecyclerView.removeView(view);
//            } catch (Exception e) {
//
//            }

        } else
            Utlies.showErrormessage(getActivity());
    }
}
