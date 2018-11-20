package learning.sudeep.imageloader.cache

import android.graphics.Bitmap
import android.util.Log
import android.util.LruCache
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


    //    private val map: MutableMap<Int, Bitmap> = WeakHashMap<Int, Bitmap>(getSize())
    //* The cache should have a configurable max capacity and should evict images not recently used;

   // private val map: MutableMap<Int, Bitmap> = HashMap<Int, Bitmap>(getSize()) //Possible memory issue #TODO with lrucache or weakhashmap // frequent GC weakhashmap is not an option

    private  var lruCache: LruCache<Int, Bitmap>

    init {

        lruCache = object : LruCache<Int, Bitmap>(getSize()) {

            override fun sizeOf(key: Int, bitmap: Bitmap): Int {
                if(getSize()!=-1){
                    return getSize()
                }
                return bitmap.byteCount / memSize()
            }
        }
    }

    companion object : SingletonHolder<MemCacheImpl>(::MemCacheImpl)

    override fun getItem(key: Int): Bitmap? {
        //map.getOrElse(key,()->null);
        synchronized(this) {
         return  lruCache.get(key)
//            Log.e("cache size ", ">> size = " + map.size)
//            if (lruCache.containsKey(key)) {
//                return map.get(key);
//            }
//            return null
        }

    }

    override fun addToCache(key: Int, byteArray: Any) {
        synchronized(this) {
            lruCache.put(key,byteArray as Bitmap)
//            limitSize(map as HashMap<Int, Any>)
//            val bitmap = byteArray as Bitmap
//            Log.e(Constant.TAG_NAME, "Bitmap size" + bitmap.byteCount)
//            map.put(key, bitmap)
//            Log.e(Constant.TAG_NAME, "map size" + map.size)
        }

    }
}