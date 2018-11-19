package com.myimage.caching.view.tiles.adapter

import android.util.Log
import com.myimage.caching.core.domain.data.tileService.Tile
import com.myimage.caching.view.binding.BaseAdapter

/**
 * Created by Sudeep SR on 19/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class TileAdapter(val viewLayout: Int, val loaderLayout: Int) : BaseAdapter() {


    private var tileList: MutableList<Tile> = mutableListOf()
    private var size: Int = 0
    private var showLoader: Boolean = false

    override fun getObjForPosition(position: Int): Any? {
         if(position<tileList.size){
             return tileList[position]
         }else{
             return null
         }
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        Log.e("show loader","show loader$showLoader,pos $position")
        if (position<tileList.size) {

            return viewLayout
        } else {
            Log.e("show loader","show loader>>")
            return loaderLayout
        }
    }

    override fun getItemCount() = size

    fun toggleLoader(showLoader: Boolean) {
        size = tileList.size
        this.showLoader = showLoader
        if (showLoader) {
            size++
        }
        notifyDataSetChanged()
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