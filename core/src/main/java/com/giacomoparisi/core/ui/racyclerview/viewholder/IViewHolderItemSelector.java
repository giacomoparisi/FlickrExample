package com.giacomoparisi.core.ui.racyclerview.viewholder;

import com.giacomoparisi.core.ui.racyclerview.item.Item;

public interface IViewHolderItemSelector {

    boolean selector(Integer position, Item item);

}
