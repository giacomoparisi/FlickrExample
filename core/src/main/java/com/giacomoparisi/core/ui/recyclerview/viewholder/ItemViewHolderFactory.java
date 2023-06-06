package com.giacomoparisi.core.ui.recyclerview.viewholder;

public class ItemViewHolderFactory {

    private final IViewHolderFactory factory;
    private IViewHolderItemSelector selector;

    public ItemViewHolderFactory(
            IViewHolderFactory factory,
            IViewHolderItemSelector selector
    ) {
        this.factory = factory;
        this.selector = selector;
    }

    public IViewHolderFactory getFactory() {
        return factory;
    }

    public IViewHolderItemSelector getSelector() {
        return selector;
    }

}


