package com.massive.bakingapp.views.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.massive.bakingapp.R;
import com.massive.bakingapp.adapters.CardAdapter;
import com.massive.bakingapp.interfaces.CallBack;
import com.massive.bakingapp.models.Ingredients;
import com.massive.bakingapp.models.Recipe;
import com.massive.bakingapp.models.Steps;
import com.massive.bakingapp.network.NetworkCheck;
import com.massive.bakingapp.network.RetroApiInterface;
import com.massive.bakingapp.network.RetrofitClient;
import com.massive.bakingapp.utlies.Utlies;
import com.massive.bakingapp.views.activity.RecipeDetailsActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardFragment extends Fragment {
    static ArrayList<Ingredients> ingredients;
    static ArrayList<Steps> steps;
    RetroApiInterface apiInterface;
    Call<ArrayList<Recipe>> call;
    ArrayList<Recipe> recipeArrayList;
    private RecyclerView recyclerView;
    private View viewRoot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.card_fragment, container, false);
        fragmentBody();
        return viewRoot;
    }

    private void fragmentBody() {
        if (NetworkCheck.isNetworkAvailable(getActivity())) {
            recyclerView = viewRoot.findViewById(R.id.recycler_view);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            apiInterface = RetrofitClient.retrofit().create(RetroApiInterface.class);
            call = apiInterface.GetRecipe();
            call.enqueue(new Callback<ArrayList<Recipe>>() {
                @Override
                public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                    recipeArrayList = response.body();

                    RecyclerView.Adapter adapter = new CardAdapter(recipeArrayList, new CallBack() {

                        @Override
                        public void getData(ArrayList<Ingredients> ingredients, ArrayList<Steps> steps) {
                            CardFragment.ingredients = ingredients;
                            CardFragment.steps = steps;
                            Intent intent = new Intent(getActivity(), RecipeDetailsActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void getId(int id) {

                        }
                    }, getActivity());
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                    Toast.makeText(getActivity(), "onFailure", Toast.LENGTH_LONG).show();
                }
            });
        } else
            Utlies.showErrormessage(getActivity());
    }


}
