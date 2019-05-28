package com.example.superdoc_ankura.activities;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;

import com.applandeo.materialcalendarview.CalendarUtils;
import com.example.superdoc_ankura.R;

/**
 * Created by Mateusz Kornakiewicz on 02.08.2018.
 */

public final class DrawableUtils {

    public static Drawable getCircleDrawableWithText(Context context, String string) {
        Drawable background = ContextCompat.getDrawable(context, R.drawable.bg_white);
        Drawable text = CalendarUtils.getDrawableText(context, string, null, android.R.color.holo_red_dark, 12);

        Drawable[] layers = {background, text};
        return new LayerDrawable(layers);
    }

    public static Drawable getDot(Context context){
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.dark_dot);

        //Add padding to too large icon
        return new InsetDrawable(drawable, 100, 0, 100, 0);
    }

    private DrawableUtils() {
    }
}
