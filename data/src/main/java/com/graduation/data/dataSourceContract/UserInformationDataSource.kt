package com.graduation.data.dataSourceContract

import com.graduation.domain.model.userinfo.UserInformationDto

interface UserInformationDataSource {
    suspend fun getUserInfo(): UserInformationDto?
}