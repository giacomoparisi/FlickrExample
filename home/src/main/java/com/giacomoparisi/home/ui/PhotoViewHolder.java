package com.giacomoparisi.home.ui;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.giacomoparisi.core.ui.racyclerview.item.Item;
import com.giacomoparisi.core.ui.racyclerview.viewholder.ItemViewHolder;
import com.giacomoparisi.core.ui.racyclerview.viewholder.ItemViewHolderFactory;
import com.giacomoparisi.core.utils.CastUtils;
import com.giacomoparisi.home.R;

public class PhotoViewHolder extends ItemViewHolder {

    private final ImageView imageView;

    public PhotoViewHolder(ViewGroup parent, int layoutId) {
        super(parent, layoutId);
        imageView = itemView.findViewById(R.id.image);
    }

    @Override
    public void bind(Item item, Integer position) {
        PhotoItem photo = CastUtils.safeCast(item);
        if (photo != null)
            Glide.with(itemView.getContext())
                    .load(photo.getPhoto().getUrl())
                    .into(imageView);
    }

    public static ItemViewHolderFactory factory() {
        return new ItemViewHolderFactory(
                group -> new PhotoViewHolder(group, R.layout.photo_item),
                (position, item) -> item instanceof PhotoItem
        );
    }
}
