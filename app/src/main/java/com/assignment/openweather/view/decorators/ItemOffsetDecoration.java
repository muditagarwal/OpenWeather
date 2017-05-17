package com.assignment.openweather.view.decorators;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

    private Rect rect;

    public ItemOffsetDecoration(int offset) {
        this.rect = new Rect(0, offset, 0, 0);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = rect.top;
    }
}
