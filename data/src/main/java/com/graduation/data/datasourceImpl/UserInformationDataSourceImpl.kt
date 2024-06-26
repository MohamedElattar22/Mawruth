package com.graduation.data.datasourceImpl

import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.UserInformationDataSource
import com.graduation.domain.model.authenticationuser.AuthenticationResponse
import javax.inject.Inject

class UserInformationDataSourceImpl @Inject constructor(
    private val webServices: WebServices
) : UserInformationDataSource {
    override suspend fun getUserInfo(): AuthenticationResponse? {
        return webServices.getUserInfo()?.toAuthenticationResponse()
    }


}