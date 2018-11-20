package learning.sudeep.imageloader.cache

import android.graphics.Bitmap
import android.util.Log
import android.util.LruCache
import learning.sudeep.imageloader.MindValleySDK
import learning.sudeep.imageloader.Request
import java.util.*

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
interface CacheRequest {


    //var lruCache: LruCache<Int, Bitmap>

    fun getItem(key: Int):Any?
    fun addToCache(key:Int,byteArray: Any)


    fun init(){

    }
    fun getSize():Int{

        return MindValleySDK.getInstance().getProperty().cacheSize
    }

//    fun limitSize(map: HashMap<Int,Any>){
//        if(map.size == MindValleySDK.getInstance().mProperty.cacheSize){
//            val key:Int=map.toList().get(0).first
//            Log.e("size limit","size limit reached"+key)
//            map.remove(key)
//        }
//    }
    fun memSize()=1024


}