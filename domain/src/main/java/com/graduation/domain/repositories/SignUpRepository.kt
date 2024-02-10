package com.graduation.domain.repositories

import com.graduation.domain.model.SignUpRequiredData
import com.graduation.domain.model.User

interface SignUpRepository {
    suspend fun registerUser(userData: SignUpRequiredData): User

}