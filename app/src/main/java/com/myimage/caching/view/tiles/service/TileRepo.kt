package com.myimage.caching.view.tiles.service


import com.myimage.caching._core.domain.api.TileService
import com.myimage.caching._core.domain.data.tile_service.Tile
import com.myimage.caching._core.service.BaseRepo
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class TileRepo @Inject constructor(val tileService: TileService):BaseRepo() {

    //Dao is not configure ideally its a combination of room + rxjava
    fun getTileList()=tileService.getTileUrls()
}