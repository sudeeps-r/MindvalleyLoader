package com.myimage.caching._core.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myimage.caching._core.di.scope.ViewModelKey
import com.myimage.caching.view.tiles.TileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */

@Module
abstract class ViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(TileViewModel::class)
    abstract fun bindTileViewModel(tileViewModel: TileViewModel):ViewModel

    @Binds
    abstract fun provideViewModelFactory(viewModelFactory: ViewModelFactory.AppViewModelFactory): ViewModelProvider.Factory

}