package com.poobest.com.mockpeoject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.poobest.com.mockpeoject.R;
import com.poobest.com.mockpeoject.holder.MenuHolder;
import com.poobest.com.mockpeoject.model.dao.MenuListItem;

import java.util.List;

public class MenuListAdapter extends RecyclerView.Adapter<MenuHolder> {

    private List<MenuListItem> list;
    private Context context;

    public MenuListAdapter(Context context, List<MenuListItem> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MenuHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final MenuHolder holder, int position) {

        MenuListItem menues = list.get(position);

        if (holder instanceof MenuHolder) {
            holder.textMenu.setText(menues.name);
            Glide.with(context).load(menues.getImgUrl()).into(holder.imgMenu);

            holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                    menu.add(holder.getAdapterPosition(), 0, 0, "abc");
                    menu.add(holder.getAdapterPosition(), 1, 0, "def");

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}