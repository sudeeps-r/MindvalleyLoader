package com.myimage.caching.view.tiles.service


import com.myimage.caching.core.domain.api.TileService
import com.myimage.caching.core.service.BaseRepo
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