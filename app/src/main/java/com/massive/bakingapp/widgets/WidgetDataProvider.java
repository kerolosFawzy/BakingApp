package com.massive.bakingapp.widgets;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.massive.bakingapp.R;
import com.massive.bakingapp.models.Ingredients;
import com.massive.bakingapp.utlies.Constants;

import java.util.ArrayList;

import io.paperdb.Paper;

/**
 * Created by minafaw on 1/15/2018.
 */

public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {

    ArrayList<Ingredients> ingredients;
    private Context context;

    public WidgetDataProvider(Context context, Intent intent) {
        this.context = context;
    }


    @Override
    public void onCreate() {
        Paper.init(context);
        ingredients = Paper.book().read(Constants.INGREDIENT_PAPER);

    }

    @Override
    public void onDataSetChanged() {
        ingredients = Paper.book().read(Constants.INGREDIENT_PAPER);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_content);
        remoteViews.setTextViewText(R.id.WidgetIngreName,ingredients.get(position).getIngredient());
        String s= String.valueOf(ingredients.get(position).getQuantity());
        remoteViews.setTextViewText(R.id.WidgetQuantity,s);
        remoteViews.setTextViewText(R.id.WidgetMeasure,ingredients.get(position).getMeasure());
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
