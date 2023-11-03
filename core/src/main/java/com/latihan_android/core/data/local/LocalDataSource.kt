package com.latihan_android.core.data.local

import com.latihan_android.core.data.local.entity.DetailEntity
import com.latihan_android.core.data.local.entity.FavoriteEntity
import com.latihan_android.core.data.local.room.DetailDao
import com.latihan_android.core.data.local.room.FavoriteDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val favoriteDao: FavoriteDao, private val detailDao: DetailDao) {

    fun getDetail(username:String):Flow<DetailEntity> = detailDao.getUser(username)

    suspend fun insertDetail(detail:DetailEntity) = detailDao.insertFavorite(detail)

    fun getAllUser(): Flow<List<FavoriteEntity>> = favoriteDao.getAllUser()

    fun getFavoriteUser():Flow<List<FavoriteEntity>> = favoriteDao.getFavoriteUser()

    suspend fun insertFavorite(userList:List<FavoriteEntity>) = favoriteDao.insertFavorite(userList)

    fun setFavoriteUser(user:FavoriteEntity, newState:Boolean) {
        user.isFavorite = newState
        favoriteDao.updateFavoriteUser(user)
    }

}