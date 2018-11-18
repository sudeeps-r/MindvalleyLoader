package com.myimage.caching.view.util

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
open class BaseFragment : Fragment(){

    protected fun showError(message:String){
        Snackbar.make(view!!,message,Snackbar.LENGTH_LONG).show()
    }
}