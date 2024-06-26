package com.graduation.domain.useCase

import com.graduation.domain.model.authenticationuser.AuthenticationResponse
import com.graduation.domain.model.authenticationuser.User
import com.graduation.domain.repositories.UserAuthenticationRepository
import javax.inject.Inject

class ConfirmEmailUseCse @Inject constructor
    (private val userAuthenticationRepository: UserAuthenticationRepository) {

    suspend fun invoke(data: User): AuthenticationResponse {
        return userAuthenticationRepository.confirmEmail(data)!!
    }
}