package com.giacomoparisi.core.ui.recyclerview.item;

public abstract class Item {

    public abstract boolean areTheSame(Item other);

    public abstract boolean haveTheSameContent(Item other);

    public final boolean compare(ItemComparator comparator, Item newItem) {
        try {
            return comparator.compare(this, newItem);
        } catch (Throwable error) {
            return false;
        }
    }

}

