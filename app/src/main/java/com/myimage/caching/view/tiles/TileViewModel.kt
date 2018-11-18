package com.myimage.caching.view.tiles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myimage.caching._core.domain.data.tile_service.ErrorResponse
import com.myimage.caching._core.domain.data.tile_service.Tile
import com.myimage.caching.view.tiles.service.TileRepo
import com.myimage.caching.view.util.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class TileViewModel @Inject constructor(val tileRepo: TileRepo):BaseViewModel() {

    private val mutableTileList:MutableLiveData<List<Tile>> by lazy {
        MutableLiveData<List<Tile>>()
    }


    //map or switch map is not needed

    fun fetchTileList(){
       compositeData.add(tileRepo.tileService.getTileUrls().observeOn(AndroidSchedulers.mainThread()).subscribeWith(object :DisposableObserver<List<Tile>>(){
           override fun onComplete() {

           }

           override fun onNext(t: List<Tile>) {
                mutableTileList.value=t
           }

           override fun onError(e: Throwable) {
                setError(ErrorResponse(e.message!!,0))
           }

       }))
    }

    fun getTileList()=this.mutableTileList

}