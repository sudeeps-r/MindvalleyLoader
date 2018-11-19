package com.myimage.caching.view.util

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myimage.caching.core.domain.data.tileService.ErrorResponse
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
open class BaseViewModel:ViewModel() {

    private var isLoading=ObservableBoolean()

    protected val error:MutableLiveData<ErrorResponse> by lazy {
        MutableLiveData<ErrorResponse>()
    }

    protected val compositeData: CompositeDisposable = CompositeDisposable()

    protected fun setError(errorResponse: ErrorResponse){
        error.value=errorResponse
    }

    fun getErrorResponse()=error

    fun getDisposable()=this.compositeData

    fun getRefreshStatus()=this.isLoading

    fun refreshStatus(refresh:Boolean){
        this.isLoading.set(refresh)
    }
}