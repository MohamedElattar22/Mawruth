package com.graduation.domain.repositories

import com.graduation.domain.model.userinfo.UserInformationDto
import java.io.File

interface EditUserInfoRepository {
    suspend fun editUserInfo(
        fullName: String?,
        userName: String?,
        email: String,
        password: String?,
        phoneNumber: String?,
        avatar: File?
    ): UserInformationDto?
}