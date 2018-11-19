package com.myimage.caching.view.binding

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Sudeep SR on 19/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class AdapterViewHolder(val binding: ViewDataBinding) :RecyclerView.ViewHolder(binding.root) {

    fun bind(obj:Any){
        binding.setVariable(BR.rowItem, obj);
        binding.executePendingBindings();
    }
}