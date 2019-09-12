package com.deb452.filterlistlib.customviews_classes.tag_layout;

import android.content.Context;
import android.graphics.Color;

/**
 * Created by Debojyoti Singha on 12,September,2019.
 */
public class Utils {

    public static float dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static float sp2px(Context context, float sp) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    /**
     * If the color is Dark, make it lighter and vice versa
     *
     * @param color in int,
     * @param factor The factor greater than 0.0 and smaller than 1.0
     * @return int
     */
    public static int manipulateColorBrightness(int color, float factor) {
        int a = Color.alpha(color);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
//        if (r + b + g < 128 * 3) factor = 1 / factor;// check if the color is bright or dark
//        r = Math.round(r * factor);
//        b = Math.round(b * factor);
//        g = Math.round(g * factor);
        if (r > 127) r = 255 - Math.round((255 - r) * factor);
        if (g > 127) g = 255 - Math.round((255 - g) * factor);
        if (b > 127) b = 255 - Math.round((255 - b) * factor);

        return Color.argb(a,
                Math.min(r, 255),
                Math.min(g, 255),
                Math.min(b, 255)
        );
    }
}