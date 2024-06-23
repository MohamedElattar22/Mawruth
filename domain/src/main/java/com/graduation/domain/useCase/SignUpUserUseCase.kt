package com.graduation.domain.useCase

import com.graduation.domain.model.VerificationResponse
import com.graduation.domain.model.authenticationuser.User
import com.graduation.domain.repositories.UserAuthenticationRepository
import javax.inject.Inject

class SignUpUserUseCase @Inject constructor(
    private val signUpRepository: UserAuthenticationRepository
) {
    suspend fun invoke(userData: User): VerificationResponse {
        return signUpRepository.registerUser(userData)!!
    }
}