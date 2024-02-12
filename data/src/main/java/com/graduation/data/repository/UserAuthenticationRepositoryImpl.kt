package com.graduation.data.repository

import com.graduation.data.dataSourceContract.UserAuthenticationDataSource
import com.graduation.domain.model.SignUpRequiredData
import com.graduation.domain.model.User
import com.graduation.domain.model.userlogin.UserLoginDto
import com.graduation.domain.model.userlogin.UserLoginPost
import com.graduation.domain.repositories.UserAuthenticationRepository
import javax.inject.Inject

class UserAuthenticationRepositoryImpl @Inject constructor
    (private val userAuthenticationDataSource: UserAuthenticationDataSource) :
    UserAuthenticationRepository {
    override suspend fun registerUser(userData: SignUpRequiredData): User {
        return userAuthenticationDataSource.signUpUser(userData)
    }

    override suspend fun loginUser(userLoginPost: UserLoginPost): UserLoginDto {
        val result = userAuthenticationDataSource.loginUser(userLoginPost)
        return result
    }
}