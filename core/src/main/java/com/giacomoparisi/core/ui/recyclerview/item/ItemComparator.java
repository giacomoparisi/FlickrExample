package com.giacomoparisi.core.ui.recyclerview.item;

public interface ItemComparator {

    boolean compare(Item currentItem, Item newItem);

}
