package learning.sudeep.imageloader

import android.os.Handler
import android.os.Looper
import learning.sudeep.imageloader.cache.CacheBuilder
import learning.sudeep.imageloader.util.SingletonHolder

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class RequestDispatcher private constructor() {

    private lateinit var requestHandler: RequestHandler

    companion object : SingletonHolder<RequestDispatcher>(::RequestDispatcher)

    fun makeRequest(request: Request): ResRequest {
        request?.let {
            if (it.requestType?.ordinal == 0) {
                handleDefaultCase(it)
            }
        }
        requestHandler = RequestHandler(request, CacheBuilder.getCacheRequest(request.requestType!!, request.cachePolicy!!))
        return requestHandler.execute()

    }

    fun handleDefaultCase(request: Request) {
        if (request.placeHolder!! > 0){

            val handler:Handler= Handler(Looper.getMainLooper())
            handler.post(Runnable {
                request.imageView?.setImageResource(request.placeHolder!!)
            })
        }



    }

    fun cancel(){
        if(::requestHandler.isInitialized){
            requestHandler.cancel()
        }
    }


}