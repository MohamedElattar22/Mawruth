package com.graduation.domain.repositories

import com.graduation.domain.model.signupdata.EmailConfirmationData
import com.graduation.domain.model.signupdata.SignUpRequiredData
import com.graduation.domain.model.signupdata.User
import com.graduation.domain.model.userlogin.UserLoginDto
import com.graduation.domain.model.userlogin.UserLoginPost

interface UserAuthenticationRepository {
    suspend fun registerUser(userData: SignUpRequiredData): User
    suspend fun loginUser(userLoginPost: UserLoginPost): UserLoginDto
    suspend fun confirmEmail(emailConfirmationData: EmailConfirmationData): String

}