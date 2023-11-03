package com.latihan_android.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.latihan_android.core.data.local.entity.DetailEntity

@Database(entities = [DetailEntity::class],version=1, exportSchema = false)
abstract class DetailDatabase : RoomDatabase(){
    abstract fun detailDao():DetailDao
}