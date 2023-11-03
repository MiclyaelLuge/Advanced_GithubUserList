package com.latihan_android.core.data.remote

import android.util.Log
import com.latihan_android.core.data.remote.network.ApiService
import com.latihan_android.core.data.remote.response.ApiResponse
import com.latihan_android.core.data.remote.response.DetailUserResponse
import com.latihan_android.core.data.remote.response.ItemsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllUser(): Flow<ApiResponse<List<ItemsItem>>>{
        return flow<ApiResponse<List<ItemsItem>>> {
            try{
                val response = apiService.getUser("A")
                val dataArray = response.items
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.items))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch(e:Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource",e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailUser(username:String):Flow<ApiResponse<DetailUserResponse>>{
        return flow{
            try{
                val response=apiService.getDetailUser(username)
                if (response.login.isNotEmpty()){
                    val dataArray=DetailUserResponse(
                        name = response.name,
                        login = response.login,
                        twitterUsername = response.twitterUsername,
                        bio = response.bio,
                        following = response.following,
                        followers = response.followers,
                        avatarUrl = response.avatarUrl,
                        nodeId = response.nodeId,
                    )
                    emit(ApiResponse.Success(dataArray))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e:Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource",e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}