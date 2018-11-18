package learning.sudeep.imageloader

import learning.sudeep.imageloader.util.SingletonHolder
import learning.sudeep.imageloader.util.SingletonHolderWithArgs

/**
 * Created by Sudeep SR on 18/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class MindValleySDK private constructor(){

    lateinit var mProperty:MProperty

    companion object : SingletonHolder<MindValleySDK>(::MindValleySDK)

    fun init(mProperty: MProperty)=apply { this.mProperty=mProperty }


    fun getProperty():MProperty{
        if(!::mProperty.isInitialized){
            init(MindvalleyBuilder.Builder().cacheSize(10).build())
        }
        return mProperty
    }

}