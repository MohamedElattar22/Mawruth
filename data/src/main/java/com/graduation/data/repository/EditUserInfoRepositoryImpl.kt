package com.graduation.data.repository

import com.graduation.data.dataSourceContract.EditUserInfoDataSource
import com.graduation.domain.model.userinfo.UserInformationDto
import com.graduation.domain.repositories.EditUserInfoRepository
import java.io.File
import javax.inject.Inject

class EditUserInfoRepositoryImpl @Inject constructor(private val editUserInfoDataSource: EditUserInfoDataSource) :
    EditUserInfoRepository {
    override suspend fun editUserInfo(
        fullName: String?,
        userName: String?,
        email: String,
        password: String?,
        phoneNumber: String?,
        avatar: File?
    ): UserInformationDto? {
        val result = editUserInfoDataSource.editUserInfo(
            fullName,
            userName,
            email,
            password,
            phoneNumber,
            avatar
        )
        return result
    }

}