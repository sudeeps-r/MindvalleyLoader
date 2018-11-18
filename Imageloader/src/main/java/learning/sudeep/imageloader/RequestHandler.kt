package learning.sudeep.imageloader

import android.graphics.Bitmap
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import learning.sudeep.imageloader.cache.CacheRequest
import learning.sudeep.imageloader.network_handler.NetworkHandler
import learning.sudeep.imageloader.thread_pool.CustomThreadPoolManager
import learning.sudeep.imageloader.util.BitmapUtil
import okhttp3.Response
import java.lang.Exception

import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import java.util.logging.Handler

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 *  T type is not handled
 */
class RequestHandler(val request: Request, val cacheRequest: CacheRequest) : Callable<NetworkWrapper> {
    override fun call(): NetworkWrapper {
        var network = NetworkWrapper(request)
        val networkRequest = NetworkHandler(request)
        val response = networkRequest.doGet()
        network = network.copy(response = response)
        handleResponse(response)
        return network
    }


    private val customThreadPoolManager = CustomThreadPoolManager.getInstance();

    fun  execute(): ResRequest {

        val cachResponse=cacheRequest.getItem(request.url.hashCode())
       cachResponse?.let{
        if(request.requestType?.ordinal==0){

            notifyClient(true,null,it as Bitmap)
        }else{
            notifyClient(true,it as String,null)
        }

            Log.e("cache","hit"+request.url)
            return ResRequest(null,customThreadPoolManager)
        }

//        cachResponse?.let {
//            Log.e("cache","hit"+request.url)
//            return ResRequest(null,customThreadPoolManager)
//        }

        Log.e("cache","Miss"+request.url)
        val future = customThreadPoolManager.addCallable(request, this)
        return ResRequest(future, customThreadPoolManager)
    }

    private fun handleResponse(response: Response) {
        val future = customThreadPoolManager.getRequest(request)

        future?.let {
            if (!it.isCancelled) {
                if(response.isSuccessful){
                    if(request.requestType?.ordinal==0){
                        val bitmap=BitmapUtil.decodeStream(response)
                        cacheRequest.addToCache(request.url.hashCode(),bitmap as Bitmap)
                        notifyClient(true,null,bitmap)
                    }else{
                        val strResponse = response.body()?.string()
                        notifyClient(true,strResponse,null)
                        cacheRequest.addToCache(request.url.hashCode(),strResponse as String)
                    }
                }else{
                    notifyClient(false,response.body().toString(),null)
                }


            }

            customThreadPoolManager.removeRequest(customThreadPoolManager.getKey(request))
        }

    }




    private fun notifyClient( isSuccess:Boolean,response:String?,bitmap: Bitmap?){
        val handler=android.os.Handler(Looper.getMainLooper());
        handler.post(Runnable {
            if (isSuccess) {
                Log.e("imageLoader",request.url)
                if (request?.requestType?.ordinal == 0) {


                    if (request?.imageView != null) {
                        request?.imageView.adjustViewBounds = true
                        bitmap?.let {
                            request?.imageView.setImageBitmap(bitmap)
                        }

                    } else if (request?.callback != null) {
                        request?.callback.onSuccess(learning.sudeep.imageloader.Response<Bitmap>(bitmap))
                    }
                } else {
                    request?.callback?.onSuccess(learning.sudeep.imageloader.Response<String>(response))
                }

            } else {
                request?.callback?.let {
                    it.onError(Exception(response))
                }
            }
        })

    }

    private fun updateCache(response: Response) {

    }

    fun cancel(){
        customThreadPoolManager.clearAllTasks()
    }
}