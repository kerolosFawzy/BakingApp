package com.massive.bakingapp.widgets;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by minafaw on 1/15/2018.
 */

public class IngredientWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetDataProvider(this.getApplicationContext(), intent);
    }
}
