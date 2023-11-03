package com.latihan_android.core.data.local.room

import androidx.room.*
import com.latihan_android.core.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    fun getAllUser(): Flow<List<FavoriteEntity>>

    @Query("SELECT * From favorite WHERE isFavorite=1")
    fun getFavoriteUser():Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(user: List<FavoriteEntity>)

    @Update
    fun updateFavoriteUser(user:FavoriteEntity)

}