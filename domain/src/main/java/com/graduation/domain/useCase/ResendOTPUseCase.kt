package com.graduation.domain.useCase

import com.graduation.domain.model.ResendOtpData
import com.graduation.domain.repositories.UserAuthenticationRepository
import javax.inject.Inject

class ResendOTPUseCase @Inject constructor(val userAuthenticationRepository: UserAuthenticationRepository) {

    suspend fun invoke(email: ResendOtpData): String {
        return userAuthenticationRepository.resendOTP(email)
    }
}