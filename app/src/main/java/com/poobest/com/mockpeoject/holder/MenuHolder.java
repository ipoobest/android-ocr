package com.poobest.com.mockpeoject.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.poobest.com.mockpeoject.R;
import com.poobest.com.mockpeoject.model.ItemClickCallback;

/**
 * Created by j.poobest on 9/24/2017 AD.
 */

public class MenuHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tvName;
    public ImageView imgView;
    private ItemClickCallback mListener;

    public MenuHolder(View itemView, ItemClickCallback listener) {
        super(itemView);
        tvName = itemView.findViewById(R.id.text_name_menu);
        imgView = itemView.findViewById(R.id.image_menu_list);
        mListener = listener;
        itemView.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        mListener.onClick(view, getAdapterPosition());

    }


//    public MenuHolder(View itemView) {
//        super(itemView);
//
//        tvName = itemView.findViewById(R.id.text_name_menu);
//        imgView = itemView.findViewById(R.id.image_menu_list);
//    }
//
//    @Override
//    public void onClick(View view, int position) {
//
//    }
}
