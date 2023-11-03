package com.latihan_android.core.domain.repository

import com.latihan_android.core.data.Resource
import com.latihan_android.core.domain.module.DetailUser
import com.latihan_android.core.domain.module.Favorite
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    fun getAllUser(): Flow<Resource<List<Favorite>>>

    fun getDetail(username: String): Flow<Resource<DetailUser>>

    fun getFavoriteUser(): Flow<List<Favorite>>

    fun setFavoriteUser(favorite: Favorite, state: Boolean)
}