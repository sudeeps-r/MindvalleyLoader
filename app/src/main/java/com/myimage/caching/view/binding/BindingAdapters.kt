package com.myimage.caching.view.binding

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.databinding.BindingAdapter
import com.myimage.caching.R
import learning.sudeep.imageloader.MindValley

/**
 * Created by Sudeep SR on 19/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
object BindingAdapters{
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage( imageView: ImageView,imageUrl:String?){

        MindValley.loadResource(imageUrl, R.drawable.ic_launcher_background,imageView)
    }

    @JvmStatic
    @BindingAdapter("preComText")
    fun preComText(textView:AppCompatTextView,preComText:String?){
        preComText?.let {
            textView.setTextFuture(PrecomputedTextCompat.getTextFuture(it, TextViewCompat.getTextMetricsParams(textView), null))
        }

    }
}