package com.graduation.data.repository

import com.graduation.data.dataSourceContract.UserAuthenticationDataSource
import com.graduation.domain.model.VerificationResponse
import com.graduation.domain.model.authenticationuser.AuthenticationResponse
import com.graduation.domain.model.authenticationuser.User
import com.graduation.domain.repositories.UserAuthenticationRepository
import javax.inject.Inject

class UserAuthenticationRepositoryImpl @Inject constructor
    (private val userAuthenticationDataSource: UserAuthenticationDataSource) :
    UserAuthenticationRepository {
    override suspend fun registerUser(userData: User): VerificationResponse? {
        return userAuthenticationDataSource.signUpUser(userData)
    }

    override suspend fun loginUser(userLoginPost: User): AuthenticationResponse? {
        val result = userAuthenticationDataSource.loginUser(userLoginPost)
        return result
    }

    override suspend fun confirmEmail(emailConfirmationData: User): AuthenticationResponse? {
        val result = userAuthenticationDataSource.verifyEmail(emailConfirmationData)
        return result
    }

    override suspend fun resendOTP(email: User): VerificationResponse? {
        return userAuthenticationDataSource.resendOTP(email)
    }

    override suspend fun forgetPassword(email: User): VerificationResponse? {
        return userAuthenticationDataSource.forgetPassword(email)
    }

    override suspend fun resetPassword(email: User, password: User): VerificationResponse? {
        return userAuthenticationDataSource.resetPassword(email, password)
    }


}