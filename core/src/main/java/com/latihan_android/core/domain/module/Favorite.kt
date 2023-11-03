package com.latihan_android.core.domain.module

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Favorite(
    val userID:String,
    val userName:String,
    val userAvatar:String,
    val isFavorite:Boolean
):Parcelable