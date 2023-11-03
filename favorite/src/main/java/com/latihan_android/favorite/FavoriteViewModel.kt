package com.latihan_android.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.latihan_android.core.domain.usecase.UserUseCase

class FavoriteViewModel(userUseCase: UserUseCase) : ViewModel() {
    val favoriteUser=userUseCase.getAllFavoriteUser().asLiveData()
}