package com.myimage.caching.core.domain.data.tileService

import com.google.gson.annotations.SerializedName

/**
 * Created by Sudeep SR on 17/11/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
data class Tile(@SerializedName("id")val id:String,@SerializedName("user")val user: User);

data class User(@SerializedName("id")val id:String,@SerializedName("name")val name:String,@SerializedName("profile_image")val profileImage: ProfileImage)

data class ProfileImage(@SerializedName("small")val small:String)