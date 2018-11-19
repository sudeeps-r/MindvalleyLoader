package com.myimage.caching.core.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class ViewModelFactory {

    @Suppress("UNCHECKED_CAST")
    class AppViewModelFactory @Inject constructor(private val viewModelMap: Map<Class<out ViewModel>,@JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val creator = viewModelMap[modelClass] ?: viewModelMap.entries.firstOrNull {
                modelClass.isAssignableFrom(it.key)
            }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
            try {
                @Suppress("UNCHECKED_CAST")
                return creator.get() as T
            } catch (e: Exception) {
                throw RuntimeException(e)
            }


        }
    }
}