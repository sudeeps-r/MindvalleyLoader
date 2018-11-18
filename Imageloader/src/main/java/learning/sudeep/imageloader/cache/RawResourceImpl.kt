package learning.sudeep.imageloader.cache

import android.graphics.Bitmap
import android.util.LruCache
import learning.sudeep.imageloader.Request
import learning.sudeep.imageloader.util.SingletonHolder
import java.util.*

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class RawResourceImpl : CacheRequest {

    // private var lruCache: LruCache<Int, String>
    private val map: MutableMap<Int, String> = HashMap<Int, String>(getSize())

    init {

//        lruCache = object : LruCache<Int, String>(getSize()) {
//
//            override fun sizeOf(key: Int, bitmap: String): Int {
//
//                return bitmap.length / memSize()
//            }
//        }
    }

    companion object : SingletonHolder<RawResourceImpl>(::RawResourceImpl)

    override fun getItem(key: Int): String? {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        return null
    }

    override fun addToCache(key: Int, byteArray: Any) {
        limitSize(map as HashMap<Int, Any>)
        map.put(key, byteArray)
    }

}