package com.graduation.data.dataSourceContract

import com.graduation.domain.model.SignUpRequiredData
import com.graduation.domain.model.User
import com.graduation.domain.model.userlogin.UserLoginDto
import com.graduation.domain.model.userlogin.UserLoginPost

interface UserAuthenticationDataSource {
    suspend fun signUpUser(userData: SignUpRequiredData): User

    suspend fun loginUser(userLoginPost: UserLoginPost): UserLoginDto
}