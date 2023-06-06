package com.giacomoparisi.core.ui.recyclerview.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;

public class AdaptiveSpacingItemDecoration extends ItemDecoration {

    private static final int NO_SPACING = 0;

    private final int size;
    private final Boolean edgeEnabled;

    public AdaptiveSpacingItemDecoration(int size, Boolean edgeEnabled) {
        this.size = size;
        this.edgeEnabled = edgeEnabled;
    }

    @Override
    public void getItemOffsets(
            @NonNull Rect outRect,
            @NonNull View view,
            @NonNull RecyclerView parent,
            @NonNull RecyclerView.State state
    ) {
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
            makeGridSpacing(
                    outRect,
                    parent.getChildAdapterPosition(view),
                    state.getItemCount(),
                    layoutManager.getOrientation(),
                    layoutManager.getSpanCount(),
                    layoutManager.getReverseLayout()
            );
        }
    }

    private void makeGridSpacing(
            Rect outRect,
            int position,
            int itemCount,
            @RecyclerView.Orientation int orientation,
            int spanCount,
            boolean isReversed
    ) {
        // Basic item positioning
        boolean isLastPosition = position == (itemCount - 1);
        int sizeBasedOnEdge = edgeEnabled ? size : NO_SPACING;
        int sizeBasedOnLastPosition = isLastPosition ? sizeBasedOnEdge : size;

        // Opposite of spanCount (find layout depth)
        int subsideCount =
                (itemCount % spanCount == 0) ?
                        itemCount / spanCount :
                        (itemCount / spanCount) + 1;

        // Grid position. Imagine all items ordered in x/y axis
        int xAxis =
                (orientation == RecyclerView.HORIZONTAL) ?
                        position / spanCount :
                        position % spanCount;

        int yAxis = (orientation == RecyclerView.HORIZONTAL) ?
                position % spanCount :
                position / spanCount;

        // Conditions in row and column
        boolean isFirstColumn = xAxis == 0;
        boolean isFirstRow = yAxis == 0;
        boolean isLastColumn =
                (orientation == RecyclerView.HORIZONTAL) ?
                        xAxis == subsideCount - 1 :
                        xAxis == spanCount - 1;
        boolean isLastRow =
                orientation == RecyclerView.HORIZONTAL ?
                        yAxis == spanCount - 1 :
                        yAxis == subsideCount - 1;

        // Saved size
        int sizeBasedOnFirstColumn = isFirstColumn ? sizeBasedOnEdge : NO_SPACING;
        int sizeBasedOnLastColumn = !isLastColumn ? sizeBasedOnLastPosition : sizeBasedOnEdge;
        int sizeBasedOnFirstRow = isFirstRow ? sizeBasedOnEdge : NO_SPACING;
        int sizeBasedOnLastRow = !isLastRow ? size : sizeBasedOnEdge;

        switch (orientation) {
            case RecyclerView.HORIZONTAL:
                // Row fixed. Number of rows is spanCount
                outRect.left = isReversed ? sizeBasedOnLastColumn : sizeBasedOnFirstColumn;
                outRect.top = edgeEnabled ?
                        size * (spanCount - yAxis) / (spanCount) :
                        size * yAxis / spanCount;
                outRect.right = isReversed ? sizeBasedOnFirstColumn : sizeBasedOnLastColumn;
                outRect.bottom = edgeEnabled ?
                        size * (yAxis + 1) / spanCount :
                        size * (spanCount - (yAxis + 1)) / spanCount;
                break;

            case RecyclerView.VERTICAL:
                // Column fixed. Number of columns is spanCount
                outRect.left = edgeEnabled ?
                        size * (spanCount - xAxis) / (spanCount) :
                        size * xAxis / spanCount;
                outRect.top = isReversed ? sizeBasedOnLastRow : sizeBasedOnFirstRow;
                outRect.right = edgeEnabled ?
                        size * (xAxis + 1) / spanCount :
                        size * (spanCount - (xAxis + 1)) / spanCount;
                outRect.bottom = isReversed ? sizeBasedOnFirstRow : sizeBasedOnLastRow;
        }
    }
}