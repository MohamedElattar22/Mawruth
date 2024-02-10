package com.graduation.data.repository

import com.graduation.data.dataSourceContract.SignUpUserDataSource
import com.graduation.domain.model.SignUpRequiredData
import com.graduation.domain.model.User
import com.graduation.domain.repositories.SignUpRepository
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor
    (private val signUpUserDataSource: SignUpUserDataSource) : SignUpRepository {
    override suspend fun registerUser(userData: SignUpRequiredData): User {
        return signUpUserDataSource.signUpUser(userData)
    }
}