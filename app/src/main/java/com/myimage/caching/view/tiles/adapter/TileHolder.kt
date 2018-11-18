package com.myimage.caching.view.tiles.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_row.view.*

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class TileHolder(view: View) : RecyclerView.ViewHolder(view) {

    val profilePic = view.ivImageIcon;
    val userName = view.tvUserName
}