package com.graduation.domain.useCase

import com.graduation.domain.model.VerificationResponse
import com.graduation.domain.model.authenticationuser.User
import com.graduation.domain.repositories.UserAuthenticationRepository
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor
    (private val userAuthenticationRepository: UserAuthenticationRepository) {
    suspend fun invoke(email: User, password: User): VerificationResponse? {
        return userAuthenticationRepository.resetPassword(email, password)
    }


}