package com.graduation.data.dataSourceContract

import com.graduation.domain.model.VerificationResponse
import com.graduation.domain.model.authenticationuser.AuthenticationResponse
import com.graduation.domain.model.authenticationuser.User

interface UserAuthenticationDataSource {
    suspend fun signUpUser(userSignUpPost: User): VerificationResponse?

    suspend fun loginUser(userLoginPost: User): AuthenticationResponse?

    suspend fun verifyEmail(verifyData: User): AuthenticationResponse?

    suspend fun resendOTP(email: User): VerificationResponse?

}