package learning.sudeep.imageloader

/**
 * Created by Sudeep SR on 18/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class MindvalleyBuilder {

    private val mProperty:MProperty= MProperty()


    class Builder{
        private var cache:Int=0;
        fun cacheSize(size:Int)=apply { this.cache=size }
        fun build():MProperty{
            return MProperty(this.cache)
        }
    }
}