package com.andrewpbrown.weatherapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

class CustomUtils {

    static Drawable getImageResource(Context context, String iconName) {
        Resources res = context.getResources();
        int resID = res.getIdentifier(iconName , "drawable", context.getPackageName());
        return context.getDrawable(resID);
    }

    static double convertWeatherToMph(double speed) {
        return speed * 2.23694;
    }
}
