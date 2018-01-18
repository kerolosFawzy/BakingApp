package com.massive.bakingapp.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.massive.bakingapp.R;
import com.massive.bakingapp.utlies.Constants;

import io.paperdb.Paper;

/**
 * Implementation of App Widget functionality.
 */
public class IngredentWidget extends AppWidgetProvider {
    static String ACTION = "Clicked";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredent_widget);
        Intent intent = new Intent(context, IngredentWidget.class);
        intent.setAction(ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        Paper.init(context);
        String ingredientName = Paper.book().read(Constants.INGREDIENT_NAME_PAPER);
        views.setTextViewText(R.id.IngreName, ingredientName);
        views.setOnClickPendingIntent(R.id.WidgetLayout, pendingIntent);

        Intent intent2 = new Intent(context, IngredientWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        views.setRemoteAdapter(appWidgetId, R.id.WidgetListView, intent2);
        // setRemoteAdapter(context, views);
//        ArrayList<Ingredients> ingredients = Paper.book().read(Constants.INGREDIENT_PAPER);
//        for (Ingredients ingredient : ingredients) {
//            RemoteViews views1 = new RemoteViews(context.getPackageName(), R.layout.widget_content);
//            views1.setTextViewText(R.id.WidgetIngreName, ingredient.getIngredient());
//            String s = String.valueOf(ingredient.getQuantity());
//            views1.setTextViewText(R.id.WidgetQuantity, s);
//            views1.setTextViewText(R.id.WidgetMeasure, ingredient.getMeasure());
//            views.addView(R.id.WidgetListView,views1);
//        }


//        for (int i = 0; i < appWidgetId.length; ++i) {
//            Intent intent1 = new Intent(context, MyWidgetService.class);
//            intent1.putExtra(appWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId[i]);
//            intent1.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
//            views.setRemoteAdapter(appWidgetId[i], R.id.WidgetListView, intent1);
//            views.setEmptyView(R.id.WidgetListView, R.id.ContentLinearWidget);
//            appWidgetManager.updateAppWidget(appWidgetId[i], views);
//        }


        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    private static void setRemoteAdapter(Context context, @NonNull final RemoteViews views) {
        views.setRemoteAdapter(R.id.WidgetListView,
                new Intent(context, IngredientWidgetService.class));
    }

    @SuppressWarnings("deprecation")
    private static void setRemoteAdapterV11(Context context, @NonNull final RemoteViews views) {
        views.setRemoteAdapter(0, R.id.WidgetListView,
                new Intent(context, IngredientWidgetService.class));
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
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

