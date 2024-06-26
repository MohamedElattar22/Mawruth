package com.graduation.data.repository

import com.graduation.data.dataSourceContract.UserInformationDataSource
import com.graduation.domain.model.authenticationuser.AuthenticationResponse
import com.graduation.domain.repositories.UserInformationRepository
import javax.inject.Inject

class UserInformationRepositoryImpl @Inject constructor(private val userInformationDataSource: UserInformationDataSource) :
    UserInformationRepository {
    override suspend fun getUserInfo(): AuthenticationResponse? {
        return userInformationDataSource.getUserInfo()
    }


}