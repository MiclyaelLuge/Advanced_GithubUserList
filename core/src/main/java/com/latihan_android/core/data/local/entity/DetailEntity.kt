package com.latihan_android.core.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName="detail")
class DetailEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="userID")
    var userID:String,

    @ColumnInfo(name="name")
    var name:String,

    @ColumnInfo(name="username")
    var username:String,

    @ColumnInfo(name="avatarUrl")
    var avatarUrl:String,

    @ColumnInfo(name="following")
    var following:Int,

    @ColumnInfo(name="followers")
    var followers:Int,

    @ColumnInfo(name="bio")
    var bio:String,

    @ColumnInfo(name="twitterUsername")
    var twitterUsername:String
)