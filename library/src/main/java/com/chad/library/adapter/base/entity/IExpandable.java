package com.chad.library.adapter.base.entity;

import java.util.List;

/**
 * implement the interface if the item_text is expandable
 * Created by luoxw on 2016/8/8.
 */
public interface IExpandable<T> {
    boolean isExpanded();
    void setExpanded(boolean expanded);
    List<T> getSubItems();

    /**
     * Get the level of this item_text. The level start from 0.
     * If you don't care about the level, just return a negative.
     */
    int getLevel();
}
