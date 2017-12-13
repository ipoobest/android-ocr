package com.poobest.com.mockpeoject.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.poobest.com.mockpeoject.R;

/**
 * Created by j.poobest on 9/24/2017 AD.
 */

public class MenuHolder extends RecyclerView.ViewHolder{

    public TextView tvName;
    public ImageView imgView;

    public MenuHolder(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.text_name_menu);
        imgView = itemView.findViewById(R.id.image_menu_list);
    }
}
