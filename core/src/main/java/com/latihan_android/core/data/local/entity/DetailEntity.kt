package com.latihan_android.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="detail")
class DetailEntity(

    @PrimaryKey
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