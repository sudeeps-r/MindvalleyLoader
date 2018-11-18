package learning.sudeep.imageloader.cache

import android.graphics.Bitmap
import learning.sudeep.imageloader.util.SingletonHolder

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class DiskCacheImpl private constructor():CacheRequest{
    override fun getItem(key: Int): ByteArray? {
       return ByteArray(1)
    }

    override fun addToCache(key: Int, byteArray: Any) {

    }


    companion object : SingletonHolder<DiskCacheImpl>(::DiskCacheImpl)

}