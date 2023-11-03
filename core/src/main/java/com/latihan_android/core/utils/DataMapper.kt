package com.latihan_android.core.utils

import com.latihan_android.core.data.local.entity.DetailEntity
import com.latihan_android.core.data.local.entity.FavoriteEntity
import com.latihan_android.core.data.remote.response.DetailUserResponse
import com.latihan_android.core.data.remote.response.ItemsItem
import com.latihan_android.core.domain.module.DetailUser
import com.latihan_android.core.domain.module.Favorite

object DataMapper {

    fun mapEntitiesToDomain(input: List<FavoriteEntity>): List<Favorite> =
        input.map {
            Favorite(
                userAvatar = it.userAvatar,
                userID = it.userId,
                userName = it.userName,
                isFavorite = it.isFavorite
            )
        }

    fun detailMapEntitiesToDomain(input: DetailEntity?): DetailUser {
        return DetailUser(
            name = input?.name.orEmpty(),
            bio = input?.bio.orEmpty(),
            userID = input?.userID.orEmpty(),
            username = input?.username.orEmpty(),
            twitterUsername = input?.twitterUsername.orEmpty(),
            following = input?.following ?: 0,
            followers = input?.followers ?: 0,
            avatarUrl = input?.avatarUrl.orEmpty()
        )
    }

    fun mapDomainToEntity(input: Favorite) = FavoriteEntity(
        userAvatar = input.userAvatar,
        userId = input.userID,
        userName = input.userName,
        isFavorite = input.isFavorite
    )

    fun mapResponseToEntities(input: List<ItemsItem>): List<FavoriteEntity> {
        val favoriteList = ArrayList<FavoriteEntity>()
        input.map {
            val favorite = FavoriteEntity(
                userId = it.nodeId,
                userAvatar = it.avatarUrl,
                userName = it.login,
                isFavorite = false
            )
            favoriteList.add(favorite)
        }
        return favoriteList
    }

    fun detailMapResponseToEntities(input: DetailUserResponse): DetailEntity {
        return DetailEntity(
            avatarUrl = input.avatarUrl,
            followers = input.followers,
            following = input.following,
            twitterUsername = input.twitterUsername ?: "This User Doesn't Have Twitter",
            username = input.login,
            bio = input.bio ?: "This User Doesn't Have Bio",
            name = input.name,
            userID = input.nodeId
        )
    }
}