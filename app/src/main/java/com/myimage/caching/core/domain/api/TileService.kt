package com.myimage.caching.core.domain.api

import com.myimage.caching.core.domain.data.tileService.Tile
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