package com.latihan_android.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="favorite")
data class FavoriteEntity(
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
