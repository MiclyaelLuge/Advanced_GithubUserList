package com.latihan_android.core.domain.usecase

import com.latihan_android.core.data.Resource
import com.latihan_android.core.domain.module.DetailUser
import com.latihan_android.core.domain.module.Favorite
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun getAllUser(): Flow<Resource<List<Favorite>>>

    fun getDetail(username: String): Flow<Resource<DetailUser>>

    fun getAllFavoriteUser(): Flow<List<Favorite>>

    fun setFavoriteUser(favorite: Favorite, state: Boolean)
}