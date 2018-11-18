package learning.sudeep.imageloader

import android.widget.ImageView
import learning.sudeep.imageloader.cache.CachePolicy
import learning.sudeep.imageloader.thread_pool.CustomThreadPoolManager
import java.util.concurrent.Future

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
data class Request(val url: String, val imageView: ImageView? = null, val requestType: RequestType = RequestType.BITMAP, val placeHolder: Int = -1, val cachePolicy: CachePolicy = CachePolicy.MEMORY, val callback: Callback<Any>? = null) {
}

enum class RequestType {
    BITMAP,
    XML,
    JSON
}

data class Response<out T>(val raw: T?)

class ResRequest(val future: Future<NetworkWrapper>?=null,val customThreadPoolManager: CustomThreadPoolManager?=null) {
    fun cancel() {
        future?.let {
            if (!it.isDone&&!it.isCancelled) {
                it.cancel(true)
            }
        }

    }

    fun cancelAll(){
        customThreadPoolManager?.let {
           it.clearAllTasks()
        }
    }
}

data class NetworkWrapper(val request: Request, val response: okhttp3.Response? = null)

interface Callback<in T> {
    fun onSuccess(response: Response<Any>)
    fun onError(e: Throwable)
}


//SDK property

data class MProperty(val cacheSize:Int=10)