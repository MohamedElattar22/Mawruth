package com.graduation.domain.repositories

import com.graduation.domain.model.VerificationResponse
import com.graduation.domain.model.authenticationuser.AuthenticationResponse
import com.graduation.domain.model.authenticationuser.User

interface UserAuthenticationRepository {
    suspend fun registerUser(userData: User): VerificationResponse?
    suspend fun loginUser(userLoginPost: User): AuthenticationResponse?
    suspend fun confirmEmail(emailConfirmationData: User): AuthenticationResponse?
    suspend fun resendOTP(email: User): VerificationResponse?

    suspend fun forgetPassword(email: User): VerificationResponse?
    suspend fun resetPassword(email: User): VerificationResponse?


}