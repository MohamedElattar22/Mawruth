package com.graduation.data.datasourceImpl

import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.UserAuthenticationDataSource
import com.graduation.domain.model.signupdata.EmailConfirmationData
import com.graduation.domain.model.signupdata.SignUpRequiredData
import com.graduation.domain.model.signupdata.User
import com.graduation.domain.model.userlogin.UserLoginDto
import com.graduation.domain.model.userlogin.UserLoginPost
import javax.inject.Inject

class UserAuthenticationDataSourceImpl @Inject constructor(private val webServices: WebServices) :
    UserAuthenticationDataSource {
    override suspend fun signUpUser(userData: SignUpRequiredData): User {
        val user = webServices.signUpUser(userData)
        return user.toUser()
    }

    override suspend fun loginUser(userLoginPost: UserLoginPost): UserLoginDto {
        val result = webServices.loginUser(userLoginPost)
        return result.toUserDto()
    }

    override suspend fun verifyEmail(verifyData: EmailConfirmationData): String {
        return webServices.verifyOTP(verifyData).message.toString()
    }

}