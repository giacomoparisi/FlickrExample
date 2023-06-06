package com.giacomoparisi.core.ui.recyclerview.viewholder;

import com.giacomoparisi.core.ui.recyclerview.item.Item;

public interface IViewHolderItemSelector {

    boolean selector(Integer position, Item item);

}
