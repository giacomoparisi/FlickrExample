package com.giacomoparisi.core.ui.racyclerview.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.giacomoparisi.core.ui.racyclerview.item.Item;
import com.giacomoparisi.core.ui.racyclerview.viewholder.ItemViewHolder;
import com.giacomoparisi.core.ui.racyclerview.viewholder.ItemViewHolderFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemsAdapter extends ListAdapter<Item, ItemViewHolder> implements IAdapter {

    private final List<ItemViewHolderFactory> factories;

    public ItemsAdapter(ItemViewHolderFactory... factories) {
        super(
                new DiffUtil.ItemCallback<Item>() {
                    @Override
                    public boolean areItemsTheSame(
                            @NonNull Item oldItem,
                            @NonNull Item newItem
                    ) {
                        return oldItem.areTheSame(newItem);
                    }

                    @Override
                    public boolean areContentsTheSame(
                            @NonNull Item newItem,
                            @NonNull Item oldItem
                    ) {
                        return oldItem.haveTheSameContent(newItem);
                    }
                }
        );

        this.factories = Arrays.asList(factories);
    }

    @Override
    public int getItemViewType(int position) {
        for (int i = 0; i < factories.size(); i++) {
            ItemViewHolderFactory factory = factories.get(i);
            if (factory.getSelector().selector(position, getItem(position)))
                return i;
        }

        throw new RuntimeException("Error defining default factory");
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType
    ) {
        ItemViewHolder viewHolder =
                factories.get(viewType).getFactory().factory(parent);
        viewHolder.setAdapter(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        // handlePaging(position)
        Item item = getItem(position);
        holder.setItem(item);
        holder.bind(item, position);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ItemViewHolder holder,
            int position,
            @NonNull List<Object> payloads
    ) {
        // handlePaging(position)
        Item item = getItem(position);
        holder.setItem(item);
        holder.bind(item, position);
    }

    @Override
    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < getItemCount(); i++) {
            items.add(getItem(i));
        }
        return items;
    }

}