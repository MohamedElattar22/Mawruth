package com.graduation.data.datasourceImpl

import com.graduation.data.dataSourceContract.SignUpUserDataSource
import com.graduation.data.model.api.WebServices
import com.graduation.domain.model.SignUpRequiredData
import com.graduation.domain.model.User
import javax.inject.Inject

class SignUpUserDataSourceImpl @Inject constructor(private val webServices: WebServices) :
    SignUpUserDataSource {
    override suspend fun signUpUser(userData: SignUpRequiredData): User {
        val user = webServices.signUpUser(userData)
        return user.toUser()
    }

}