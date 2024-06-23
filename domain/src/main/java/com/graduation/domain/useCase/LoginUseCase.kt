package com.graduation.domain.useCase

import com.graduation.domain.model.authenticationuser.AuthenticationResponse
import com.graduation.domain.model.authenticationuser.User
import com.graduation.domain.repositories.UserAuthenticationRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val userAuthenticationRepository: UserAuthenticationRepository) {

    suspend fun invoke(user: User): AuthenticationResponse {
        val result = userAuthenticationRepository.loginUser(user)
        return result!!
    }
}