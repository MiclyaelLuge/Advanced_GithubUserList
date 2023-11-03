package com.latihan_android.core.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName="favorite")
data class FavoriteEntity(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name="userId")
    var userId :String,

    @ColumnInfo(name="userName")
    var userName:String,

    @ColumnInfo(name="userAvatar")
    var userAvatar:String,

    @ColumnInfo(name="isFavorite")
    var isFavorite:Boolean = false


)
