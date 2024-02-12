package com.graduation.domain.useCase

import com.graduation.domain.model.EmailConfirmationData
import com.graduation.domain.repositories.UserAuthenticationRepository
import javax.inject.Inject

class ConfirmEmailUseCse @Inject constructor(private val userAuthenticationRepository: UserAuthenticationRepository) {

    suspend fun invoke(data: EmailConfirmationData): String {
        return userAuthenticationRepository.confirmEmail(data)
    }


}