package com.massive.bakingapp.views;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.massive.bakingapp.CardAdapter;
import com.massive.bakingapp.R;
import com.massive.bakingapp.interfaces.CallBack;
import com.massive.bakingapp.models.Ingredients;
import com.massive.bakingapp.models.Recipe;
import com.massive.bakingapp.models.Steps;
import com.massive.bakingapp.network.NetworkCheck;
import com.massive.bakingapp.network.RetroApiInterface;
import com.massive.bakingapp.network.RetrofitClient;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private View viewRoot;
    RetroApiInterface apiInterface;
    Call<Recipe> call;
    static ArrayList<Ingredients> ingredients;
    static ArrayList<Steps> steps;
    ArrayList<Recipe> recipeArrayList;
    CallBack callBack;

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
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            apiInterface = RetrofitClient.retrofit().create(RetroApiInterface.class);
            call = apiInterface.GetRecipe();
            call.enqueue(new Callback<Recipe>() {
                @Override
                public void onResponse(Call<Recipe> call, Response<Recipe> response) {
//                    ingredients=response.body().getIngredients();
//                    steps=response.body().getSteps();
//                    for (int i = 1; i <= 4; i++) {}
                    if (response.body().getId() != null)
                        recipeArrayList = response.body().getRecipes();

                    RecyclerView.Adapter adapter = new CardAdapter(recipeArrayList, new CallBack() {

                        @Override
                        public void getData(ArrayList<Ingredients> ingredients, ArrayList<Steps> steps) {
                            CardFragment.ingredients = ingredients;
                            CardFragment.steps = steps;
                        }
                    }, getActivity());
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<Recipe> call, Throwable t) {

                }
            });
        } else
            showErrormessage();
    }

    public void showErrormessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("No Internet");
        builder.setMessage("Internet is required. Please Retry.");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                fragmentBody();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        Toast.makeText(getActivity(), "Network Unavailable!", Toast.LENGTH_LONG).show();
    }
}
