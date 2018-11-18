package com.myimage.caching.view.tiles.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.PrecomputedTextCompat
import androidx.core.text.TextDirectionHeuristicCompat
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.myimage.caching.R
import com.myimage.caching._core.domain.data.tile_service.Tile
import com.myimage.caching.view.tiles.TileViewModel
import com.myimage.caching.view.util.LoadMoreHolder
import learning.sudeep.imageloader.MindValley

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class TileAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var tileList: MutableList<Tile> = mutableListOf()
    private var size: Int = 0
    private var showLoader: Boolean = false
    private val VIEW: Int = 0;
    private val LOADER: Int = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_row, parent, false)
            return TileHolder(view)
        } else {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.load_more, parent, false)
            return LoadMoreHolder(view)
        }

    }

    override fun getItemCount() = size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        if (viewType == VIEW) {
            val tileHolder = holder as TileHolder
            val tile = tileList[position]
            tileHolder.userName.setTextFuture(PrecomputedTextCompat.getTextFuture(tile?.user?.name, TextViewCompat.getTextMetricsParams(holder.userName), null))

            MindValley.loadResource(tile.user.profileImage.small, R.drawable.ic_launcher_background,holder.profilePic)
        }


    }

    fun showLoader(show: Boolean) {
        size = tileList.size
        if (show) {
            size++;
        }
        this.showLoader = show
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (this.showLoader && position == tileList.size) {
            return LOADER
        } else {
            return VIEW
        }
        //return super.getItemViewType(position)
    }

    fun addItems(list: List<Tile>, isAppend: Boolean) {
        if (!isAppend) {
            tileList.clear()
        }
        list?.let {
            tileList.addAll(it)
            size = tileList.size
        }
        if (list == null) size = 0
        this.showLoader = false
        notifyDataSetChanged()
    }
}