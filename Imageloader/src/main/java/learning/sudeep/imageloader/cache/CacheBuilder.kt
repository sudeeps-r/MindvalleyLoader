package learning.sudeep.imageloader.cache

import learning.sudeep.imageloader.RequestType

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class CacheBuilder() {
    companion object {
        fun getCacheRequest(requestType: RequestType,cachePolicy: CachePolicy):CacheRequest{
            if(cachePolicy.ordinal==0){
                if(requestType.ordinal==0){
                    return MemCacheImpl.getInstance()
                }else{
                    return RawResourceImpl.getInstance()
                }

            }else{
                return DiskCacheImpl.getInstance()
            }
        }
    }
}