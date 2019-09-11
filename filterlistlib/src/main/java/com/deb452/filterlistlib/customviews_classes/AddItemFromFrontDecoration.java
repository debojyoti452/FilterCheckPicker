package com.deb452.filterlistlib.customviews_classes;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Created by Debojyoti Singha on 11,September,2019.
 */
public class AddItemFromFrontDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, @NotNull RecyclerView.State state) {
        int parentWidth = parent.getWidth();
        int childWidth = view.getWidth();
        int margin = (parentWidth - childWidth) / 2;

        int position = parent.getChildAdapterPosition(view);

        outRect.left = position == 0 ? margin : 0;
        outRect.right = position == (Objects.requireNonNull(parent.getAdapter()).getItemCount() - 1) ? margin : 0;
    }
}
