package com.latihan_android.expert_submission.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.latihan_android.core.domain.module.Favorite
import com.latihan_android.core.domain.usecase.UserUseCase

class DetailViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    fun getDetail(username:String) = userUseCase.getDetail(username).asLiveData()
    fun setFavoriteUser(favorite: Favorite, newStatus:Boolean) = userUseCase.setFavoriteUser(favorite,newStatus)
}