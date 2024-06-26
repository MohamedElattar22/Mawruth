package com.graduation.data.datasourceImpl

import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.UserAuthenticationDataSource
import com.graduation.domain.model.VerificationResponse
import com.graduation.domain.model.authenticationuser.AuthenticationResponse
import com.graduation.domain.model.authenticationuser.User
import javax.inject.Inject

class UserAuthenticationDataSourceImpl @Inject constructor(private val webServices: WebServices) :
    UserAuthenticationDataSource {
    override suspend fun signUpUser(userSignUpPost: User): VerificationResponse? {
        return webServices.signUpUser(userSignUpPost)?.toVerificationResponse()
    }

    override suspend fun loginUser(userLoginPost: User): AuthenticationResponse? {
        val result = webServices.loginUser(userLoginPost)
        return result?.toAuthenticationResponse()
    }

    override suspend fun verifyEmail(verifyData: User): AuthenticationResponse? {
        return webServices.verifyOTP(verifyData)?.toAuthenticationResponse()
    }

    override suspend fun resendOTP(email: User): VerificationResponse? {
        return webServices.resendOTP(email)?.toVerificationResponse()
    }

    override suspend fun forgetPassword(email: User): VerificationResponse? {
        return webServices.forgetPassword(email)?.toVerificationResponse()
    }

    override suspend fun resetPassword(email: User, password: User): VerificationResponse? {
        return webServices.resetPassword(email, password)?.toVerificationResponse()
    }
}