package com.myimage.caching.core.di.component

import com.myimage.caching.MindvalleyLoader
import com.myimage.caching.core.di.module.ActivityModule
import com.myimage.caching.core.di.module.ServiceModule
import com.myimage.caching.core.di.module.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class,ViewModelModule::class,ActivityModule::class,ServiceModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder{
        fun build():AppComponent
    }
    fun inject(applicaitonContext:MindvalleyLoader)
}