package com.graduation.data.dataSourceContract

import com.graduation.domain.model.SignUpRequiredData
import com.graduation.domain.model.User

interface SignUpUserDataSource {
    suspend fun signUpUser(userData: SignUpRequiredData): User
}