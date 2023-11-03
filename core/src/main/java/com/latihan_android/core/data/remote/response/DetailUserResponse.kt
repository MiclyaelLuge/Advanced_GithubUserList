package com.latihan_android.core.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class DetailUserResponse(
	@field:SerializedName("twitter_username")
	val twitterUsername: String?,

	@field:SerializedName("bio")
	val bio: String?,

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("followers")
	val followers: Int,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("following")
	val following: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("node_id")
	val nodeId: String
) : Parcelable
