package com.myimage.caching.view.binding

import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup


/**
 * Created by Sudeep SR on 19/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
abstract class BaseAdapter : RecyclerView.Adapter<AdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): AdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
                layoutInflater, viewType, parent, false)
        return AdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder,
                                  position: Int) {
        val obj = getObjForPosition(position)
        obj?.let {
            holder.bind(it)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    protected abstract fun getObjForPosition(position: Int): Any?

    protected abstract fun getLayoutIdForPosition(position: Int): Int
}