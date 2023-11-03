package com.latihan_android.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.latihan_android.core.data.local.entity.DetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailDao {
    @Query("SELECT * FROM detail WHERE username = :username")
    fun getUser(username:String): Flow<DetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(detail : DetailEntity)
}