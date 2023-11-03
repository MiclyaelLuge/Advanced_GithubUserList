package com.latihan_android.expert_submission.di

import com.latihan_android.core.domain.usecase.UserInteractor
import com.latihan_android.core.domain.usecase.UserUseCase
import com.latihan_android.expert_submission.detail.DetailViewModel
import com.latihan_android.expert_submission.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UserUseCase> { UserInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}