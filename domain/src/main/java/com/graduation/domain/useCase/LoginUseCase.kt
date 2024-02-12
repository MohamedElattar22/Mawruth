package com.graduation.domain.useCase

import com.graduation.domain.model.userlogin.UserLoginDto
import com.graduation.domain.model.userlogin.UserLoginPost
import com.graduation.domain.repositories.UserAuthenticationRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val userAuthenticationRepository: UserAuthenticationRepository) {

    suspend fun invoke(user: UserLoginPost): UserLoginDto {
        val result = userAuthenticationRepository.loginUser(user)
        return result
    }
}