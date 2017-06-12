package com.mishkaowner.baselibrary.util;

/**
 * Created by jhkim on 17. 6. 12.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
