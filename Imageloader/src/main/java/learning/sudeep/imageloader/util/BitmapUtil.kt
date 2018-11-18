package learning.sudeep.imageloader.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.util.TypedValue
import okhttp3.Request
import okhttp3.Response
import okio.*
import java.io.IOException
import java.nio.ByteBuffer

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class BitmapUtil {

    //No bitmap optimisation is done

    companion object {
        fun decodeStream(response: Response):Bitmap?{
            if(response.body()!=null){
                return BitmapFactory.decodeStream(response.body()?.byteStream())
            }else{
                return null
            }

        }

    }
}