package learning.sudeep.imageloader

import android.content.Context
import android.widget.ImageView
import learning.sudeep.imageloader.cache.CachePolicy
import learning.sudeep.imageloader.util.SingletonHolder
import java.util.*

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
 class MindValley {



//Optional parameters is throwing error with enum
 //TODO higher order function

    companion object {

        fun loadResource(url: String?, placeHolder: Int = -1,requestType: RequestType = RequestType.BITMAP, cachePolicy: CachePolicy = CachePolicy.MEMORY, imageView: ImageView? = null, callBack:Callback<Any>? = null): ResRequest {

            url?.let {
                val request = Request(url, imageView, requestType, placeHolder, cachePolicy, callBack)
                return RequestDispatcher.getInstance().makeRequest(request)
            }

            return ResRequest()
        }
        fun loadResource(url: String?, placeHolder: Int = -1, imageView: ImageView? = null,cachePolicy: CachePolicy = CachePolicy.MEMORY): ResRequest {

            url?.let {
                val request = Request(url, imageView, RequestType.BITMAP,placeHolder, cachePolicy, null)
                return RequestDispatcher.getInstance().makeRequest(request)
            }

            return ResRequest()
        }
        fun loadResource(url: String?,requestType: RequestType = RequestType.JSON, callBack:Callback<Any>? = null,cachePolicy: CachePolicy = CachePolicy.MEMORY): ResRequest {

            url?.let {
                val request = Request(url, null,requestType, -1, cachePolicy, callBack)
                return RequestDispatcher.getInstance().makeRequest(request)
            }

            return ResRequest()
        }

//        fun <T>loadResource(url: String,  callback: Callback<T> = null, requestType: RequestType = RequestType.BITMAP, cachePolicy: CachePolicy = CachePolicy.MEMORY,onSuccess:(response:Response)->Unit,onError:(t:Throwable)->Unit: ResRequest {
//
//            url?.let {
//
//                val request = Request(url, requestType, cachePolicy, callback)
//                return RequestDispatcher.getInstance().makeRequest(request)
//            }
//
//            return ResRequest()
//        }
        @JvmStatic
        fun cancelAll(){
            RequestDispatcher.getInstance().cancel()
        }
    }




}