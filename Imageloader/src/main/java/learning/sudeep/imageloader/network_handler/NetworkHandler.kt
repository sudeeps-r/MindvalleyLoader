package learning.sudeep.imageloader.network_handler

import learning.sudeep.imageloader.Request
import okhttp3.OkHttpClient
import okhttp3.Response
import java.net.CacheRequest

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class NetworkHandler(val request:Request) {

    var client = OkHttpClient()
    init {

    }




    fun execute(){


    }

    fun doGet():Response{
        val request =okhttp3.Request.Builder().url(this.request.url).build()
        return run(request)
    }
    @Throws(Exception::class)
    fun run(request: okhttp3.Request) :Response{

        val response = client.newCall(request).execute()
        return response

    }

    fun GET(){

    }


}