package com.graduation.data.dataSourceContract

import com.graduation.domain.model.userinfo.UserInformationDto
import java.io.File

interface EditUserInfoDataSource {
    suspend fun editUserInfo(
        fullName: String? = null,
        userName: String? = null,
        email: String? = null,
        password: String? = null,
        phoneNumber: String? = null,
        avatar: File? = null
    ): UserInformationDto?
}