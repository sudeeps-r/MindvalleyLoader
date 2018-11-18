package com.myimage.caching.view.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myimage.caching._core.domain.data.tile_service.ErrorResponse
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
open class BaseViewModel:ViewModel() {

    protected val error:MutableLiveData<ErrorResponse> by lazy {
        MutableLiveData<ErrorResponse>()
    }

    protected val compositeData: CompositeDisposable = CompositeDisposable()

    protected fun setError(errorResponse: ErrorResponse){
        error.value=errorResponse
    }

    fun getErrorResponse()=error

    fun getDisposable()=this.compositeData
}