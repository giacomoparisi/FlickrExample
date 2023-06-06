package com.giacomoparisi.core.ui.recyclerview.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.giacomoparisi.core.ui.recyclerview.adapter.IAdapter;
import com.giacomoparisi.core.ui.recyclerview.item.Item;

import java.util.List;

public abstract class ItemViewHolder extends RecyclerView.ViewHolder {

    private Item item;
    private IAdapter adapter;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public ItemViewHolder(ViewGroup parent, @LayoutRes int layoutId) {
        super(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(layoutId, parent, false)
        );
    }

    public abstract void bind(Item item, Integer position);


    public final List<Item> getItems() {
        return adapter.getItems();
    }

    public final void setAdapter(IAdapter adapter) {
        this.adapter = adapter;
    }

    public final void setItem(Item item) {
        this.item = item;
    }
}