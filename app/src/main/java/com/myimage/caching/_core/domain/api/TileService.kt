package com.myimage.caching._core.domain.api

import com.myimage.caching._core.domain.data.tile_service.Tile
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
interface TileService {

    @GET("raw/wgkJgazE")
    fun getTileUrls():Observable<List<Tile>>
}