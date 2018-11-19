package com.myimage.caching.view.util

import kotlin.reflect.KProperty

/**
 * Created by Sudeep SR on 19/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */

public interface ReadWriteProperty<in R, T> {
    /**
     * Returns the value of the property for the given object.
     * @param thisRef the object for which the value is requested.
     * @param property the metadata for the property.
     * @return the property value.
     */
    public operator fun getValue(thisRef: R, property: KProperty<*>): T

    /**
     * Sets the value of the property for the given object.
     * @param thisRef the object for which the value is requested.
     * @param property the metadata for the property.
     * @param value the value to set.
     */
    public operator fun setValue(thisRef: R, property: KProperty<*>, value: T)
}
