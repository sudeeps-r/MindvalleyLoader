package com.myimage.caching.core.di.module


import com.myimage.caching.core.domain.api.TileService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
@Module(includes = [HttpClient::class])
class ServiceModule {

    @Provides
    @Singleton
    fun bindTileService(retrofit: Retrofit) = retrofit.create(TileService::class.java)

}