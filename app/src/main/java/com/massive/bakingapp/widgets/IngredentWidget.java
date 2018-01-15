package com.massive.bakingapp.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.massive.bakingapp.R;
import com.massive.bakingapp.models.Ingredients;
import com.massive.bakingapp.utlies.Constants;

import java.util.ArrayList;

import io.paperdb.Paper;

/**
 * Implementation of App Widget functionality.
 */
public class IngredentWidget extends AppWidgetProvider {
    static String ACTION = "Clicked";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        Intent intent = new Intent(context, IngredentWidget.class);
        intent.setAction(ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);


        Paper.init(context);
        ArrayList<Ingredients> ingredients = Paper.book().read(Constants.INGREDIENT_PAPER);
        String ingredientName = Paper.book().read(Constants.INGREDIENT_NAME_PAPER);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredent_widget);
        views.setTextViewText(R.id.IngreName, ingredientName);
        views.setOnClickPendingIntent(R.id.WidgetLayout, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals(ACTION)) {
            // Intent intent1= new Intent(this, MainActivity.class);
            Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

