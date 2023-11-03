package com.latihan_android.core.domain.usecase

import com.latihan_android.core.domain.module.Favorite
import com.latihan_android.core.domain.repository.IUserRepository

class UserInteractor(private val userRepository: IUserRepository):UserUseCase {
    override fun getAllUser() = userRepository.getAllUser()

    override fun getDetail(username: String) = userRepository.getDetail(username)

    override fun getAllFavoriteUser() = userRepository.getFavoriteUser()

    override fun setFavoriteUser(favorite: Favorite, state: Boolean) = userRepository.setFavoriteUser(favorite, state)
}