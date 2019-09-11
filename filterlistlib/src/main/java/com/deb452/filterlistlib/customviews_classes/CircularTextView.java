package com.deb452.filterlistlib.customviews_classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.deb452.filterlistlib.R;

import java.util.Random;

/**
 * Created by Debojyoti Singha on 11,September,2019.
 */
public class CircularTextView extends AppCompatTextView {

    private String fetchedText;
    private Paint solidPaint, strokePaint;
    private int strokeColor, solidColor;
    private float strokeWidth;
    private float defaultSize = -1f;
    private Context context;
    private int randomAndroidColor;
    private int color = -1;
    private int[] androidColors;

    public CircularTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    private void initViews(Context context, AttributeSet attrs) {
        this.context = context;
        solidPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        androidColors = context.getResources().getIntArray(R.array.androidcolors);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        final int diameter, radius, height, width;

        solidPaint.setColor(solidColor);
        strokePaint.setColor(strokeColor);

        height = this.getHeight();
        width = this.getWidth();

        diameter = ((height > width) ? height : width);
        radius = diameter / 2;

        this.setHeight(diameter);
        this.setWidth(diameter);
        this.setText(fetchedText.toUpperCase());

        if (defaultSize != -1f) {
            this.setTextSize(defaultSize);
        } else {
            this.setTextSize(diameter / 5);
        }

        canvas.drawCircle(diameter / 2, diameter / 2, radius - strokeWidth, solidPaint);
        super.onDraw(canvas);
    }

    public void setStrokeWidth(int dp) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        this.strokeWidth = dp * scale;
    }

    public void setStrokeColor(int color) {
        this.strokeColor = color;
    }

    public void setSolidColor(int pos) {
        if (LoadCircleColor(pos) == -1) {
            //first start save color in shared prefs
            this.solidColor = Color.parseColor(genRandomColor());
            setCircleColor(pos, this.solidColor);
        } else {
            //color has been saved load from shared prefs
            this.solidColor = LoadCircleColor(pos);
        }

    }

    public void setSolidColor() {
        this.solidColor = Color.parseColor(genRandomColor());
    }

    public void setCustomText(String value) {
        this.fetchedText = String.valueOf(value.charAt(0)) + value.charAt(1);
    }

    public void setCustomTextSize(float value) {
        this.defaultSize = value;
    }

    //Generate Random Color
    public String genRandomColor() {
        randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        String hexColor = "#" + Integer.toHexString(randomAndroidColor).substring(2);
        return hexColor.toString();
    }

    //Save Circle Color in Shared Prefs
    public void setCircleColor(int position, int color) {
        SharedPreferences preferences;
        SharedPreferences.Editor editor;
        preferences = context.getSharedPreferences("colors", Context.MODE_PRIVATE);
        editor = preferences.edit().putInt(Integer.toString(position), color);
        editor.apply();
    }

    //Retrieve Circle Color from Shared Prefs
    public int LoadCircleColor(int position) {
        SharedPreferences preferences = context.getSharedPreferences("colors", Context.MODE_PRIVATE);
        color = preferences.getInt(Integer.toString(position), -1);
        return color;
    }
}
