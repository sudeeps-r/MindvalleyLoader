package com.myimage.caching._core.di.component

import android.content.Context
import com.myimage.caching.MindvalleyLoader
import com.myimage.caching._core.di.module.ActivityModule
import com.myimage.caching._core.di.module.AppModule
import com.myimage.caching._core.di.module.ServiceModule
import com.myimage.caching._core.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjection
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