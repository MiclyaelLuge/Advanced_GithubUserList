package com.latihan_android.expert_submission.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.latihan_android.core.domain.usecase.UserUseCase

class HomeViewModel(userUseCase: UserUseCase) : ViewModel() {
    val user = userUseCase.getAllUser().asLiveData()
}