package com.latihan_android.core.data

import com.latihan_android.core.data.local.LocalDataSource
import com.latihan_android.core.data.remote.RemoteDataSource
import com.latihan_android.core.data.remote.response.ApiResponse
import com.latihan_android.core.data.remote.response.DetailUserResponse
import com.latihan_android.core.data.remote.response.ItemsItem
import com.latihan_android.core.domain.module.DetailUser
import com.latihan_android.core.domain.module.Favorite
import com.latihan_android.core.domain.repository.IUserRepository
import com.latihan_android.core.utils.AppExecutors
import com.latihan_android.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IUserRepository {
    override fun getAllUser(): Flow<Resource<List<Favorite>>> =
        object :
            NetworkBoundResource<List<Favorite>, List<ItemsItem>>() {
            override fun loadFromDB(): Flow<List<Favorite>> {
                return localDataSource.getAllUser().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ItemsItem>>> =
                remoteDataSource.getAllUser()

            override suspend fun saveCallResult(data: List<ItemsItem>) {
                val favoriteList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertFavorite(favoriteList)
            }

            override fun shouldFetch(data: List<Favorite>?): Boolean = data.isNullOrEmpty()

        }.asFlow()

    override fun getDetail(username: String): Flow<Resource<DetailUser>> =
        object :
            NetworkBoundResource<DetailUser, DetailUserResponse>() {
            override fun loadFromDB(): Flow<DetailUser> {
                return localDataSource.getDetail(username)
                    .map { DataMapper.detailMapEntitiesToDomain(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<DetailUserResponse>> =
                remoteDataSource.getDetailUser(username)

            override suspend fun saveCallResult(data: DetailUserResponse) {
                val detailData = DataMapper.detailMapResponseToEntities(data)
                localDataSource.insertDetail(detailData)
            }

            override fun shouldFetch(data: DetailUser?): Boolean = data?.username.isNullOrEmpty()

        }.asFlow()


    override fun getFavoriteUser(): Flow<List<Favorite>> {
        return localDataSource.getFavoriteUser().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteUser(favorite: Favorite, state: Boolean) {
        val favoriteEntity = DataMapper.mapDomainToEntity(favorite)
        appExecutors.diskIO().execute { localDataSource.setFavoriteUser(favoriteEntity, state) }
    }

}