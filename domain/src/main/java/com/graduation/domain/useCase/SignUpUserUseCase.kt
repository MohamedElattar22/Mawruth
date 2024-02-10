package com.graduation.domain.useCase

import com.graduation.domain.model.SignUpRequiredData
import com.graduation.domain.model.User
import com.graduation.domain.repositories.SignUpRepository
import javax.inject.Inject

class SignUpUserUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend fun invoke(userData: SignUpRequiredData): User {
        return signUpRepository.registerUser(userData)
    }
}