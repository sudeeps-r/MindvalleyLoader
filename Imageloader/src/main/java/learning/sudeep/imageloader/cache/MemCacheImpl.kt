package learning.sudeep.imageloader.cache

import android.graphics.Bitmap
import android.util.Log
import learning.sudeep.imageloader.MindValleySDK
import learning.sudeep.imageloader.util.Constant
import learning.sudeep.imageloader.util.SingletonHolder
import java.util.*

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class MemCacheImpl private constructor() : CacheRequest {

    //   private var lruCache: LruCache<Int, Bitmap>
    //    private val map: MutableMap<Int, Bitmap> = WeakHashMap<Int, Bitmap>(getSize())
    //* The cache should have a configurable max capacity and should evict images not recently used;

    private val map: MutableMap<Int, Bitmap> = HashMap<Int, Bitmap>(getSize())

    init {

//        lruCache = object : LruCache<Int, Bitmap>(getSize()) {
//
//            override fun sizeOf(key: Int, bitmap: Bitmap): Int {
//
//                return bitmap.byteCount / memSize()
//            }
//        }
    }

    companion object : SingletonHolder<MemCacheImpl>(::MemCacheImpl)

    override fun getItem(key: Int): Bitmap? {
        //map.getOrElse(key,()->null);
        synchronized(this) {
            Log.e("cache size ", ">> size = " + map.size)
            if (map.containsKey(key)) {
                return map.get(key);
            }
            return null
        }

    }

    override fun addToCache(key: Int, byteArray: Any) {
        synchronized(this) {
            limitSize(map as HashMap<Int, Any>)
            val bitmap = byteArray as Bitmap
            Log.e(Constant.TAG_NAME, "Bitmap size" + bitmap.byteCount)
            map.put(key, bitmap)
            Log.e(Constant.TAG_NAME, "map size" + map.size)
        }

    }
}