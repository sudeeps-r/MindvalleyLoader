package learning.sudeep.imageloader.thread_pool

import android.os.Process
import java.util.concurrent.ThreadFactory

import android.util.Log
import learning.sudeep.imageloader.util.Constant


/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class BackgroundThreadFactory : ThreadFactory {
    override fun newThread(runnable: Runnable?): Thread {
        val thread = Thread(runnable)
        thread.setName("CustomThread")
        thread.setPriority(Process.THREAD_PRIORITY_BACKGROUND)
        // A exception handler is created to log the exception from threads
        thread.setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler { thread, ex -> Log.e(Constant.TAG_NAME, thread.name + " encountered an error: " + ex.message) })
        return thread
    }
}