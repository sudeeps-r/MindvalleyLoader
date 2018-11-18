package learning.sudeep.imageloader.thread_pool


import learning.sudeep.imageloader.NetworkWrapper
import learning.sudeep.imageloader.Request
import learning.sudeep.imageloader.util.SingletonHolder
import okhttp3.Response
import java.util.concurrent.*

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class CustomThreadPoolManager private constructor() {


    private val NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors()
    private val KEEP_ALIVE_TIME_UNIT: TimeUnit = TimeUnit.SECONDS
    private val KEEP_ALIVE_TIME: Long = 1
    private lateinit var executerServie: ExecutorService
    private val list = arrayListOf<Future<NetworkWrapper>>()
    private val map: MutableMap<Int, Future<NetworkWrapper>> = HashMap<Int, Future<NetworkWrapper>>()

    private val mTaskQueue: BlockingQueue<Runnable>  by lazy {
        LinkedBlockingQueue<Runnable>()
    }

    init {
        executerServie = ThreadPoolExecutor(NUMBER_OF_CORES, NUMBER_OF_CORES * 2, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, mTaskQueue, BackgroundThreadFactory())
    }

    companion object : SingletonHolder<CustomThreadPoolManager>(::CustomThreadPoolManager)

    fun addCallable(request: Request, callable: Callable<NetworkWrapper>): Future<NetworkWrapper> {
        val key: Int = getKey(request)
        removeRequest(key)
        if (!map.containsKey(key)) {
            var future = executerServie.submit(callable)
            list.add(future)
            if (key > 0)
                map.put(key, future)
            return future;
        } else {
            return map.get(key)!!
        }

    }

     fun getKey(request: Request): Int {
        request?.let {
            it.imageView?.let {
                return it.hashCode()
            }
            it.callback?.let {
                return it.hashCode()
            }
        }

        return -1;
    }

    fun getRequest(request: Request): Future<NetworkWrapper>? {
        return map.get(getKey(request))
    }

    fun removeRequest(key: Int) {

        if (map.containsKey(key)) {
            val future = map.get(key)
            future?.let {
                if (it.isCancelled || it.isDone) {
                    map.remove(key)
                }
            }
        }
    }

    fun clearAllTasks() {
        synchronized(this) {
            mTaskQueue.clear()
            for (task in list) {
                if (!task.isDone()) {
                    task.cancel(true)
                }
            }
            map.clear()
            list.clear()


        }
    }
}