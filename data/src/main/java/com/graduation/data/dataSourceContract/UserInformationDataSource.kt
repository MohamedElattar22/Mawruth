package com.graduation.data.dataSourceContract

import com.graduation.domain.model.authenticationuser.AuthenticationResponse

interface UserInformationDataSource {
    suspend fun getUserInfo(): AuthenticationResponse?
}