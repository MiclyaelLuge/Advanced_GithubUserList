package com.latihan_android.core.domain.module

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailUser(
    val userID:String,
    val followers:Int,
    val following:Int,
    val avatarUrl:String,
    val name:String,
    val username:String,
    val bio :String,
    val twitterUsername:String,

):Parcelable
