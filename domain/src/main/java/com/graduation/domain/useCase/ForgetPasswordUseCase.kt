package com.graduation.domain.useCase

import com.graduation.domain.model.VerificationResponse
import com.graduation.domain.model.authenticationuser.User
import com.graduation.domain.repositories.UserAuthenticationRepository
import javax.inject.Inject

class ForgetPasswordUseCase @Inject constructor(private val userAuthenticationRepository: UserAuthenticationRepository) {
    suspend fun invoke(email: User): VerificationResponse? {
        return userAuthenticationRepository.forgetPassword(email)
    }
}