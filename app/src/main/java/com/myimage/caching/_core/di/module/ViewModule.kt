package com.myimage.caching._core.di.module

import com.myimage.caching.HomeScreen
import com.myimage.caching.view.tiles.TileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun  contributeHome():HomeScreen

}


@Module
abstract class FragmentModule{

    @ContributesAndroidInjector
    abstract fun contributeTileFragment():TileFragment

}