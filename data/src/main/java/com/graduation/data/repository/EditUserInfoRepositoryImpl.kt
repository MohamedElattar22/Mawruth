package com.graduation.data.repository

import com.graduation.data.dataSourceContract.EditUserInfoDataSource
import com.graduation.domain.model.authenticationuser.AuthenticationResponse
import com.graduation.domain.repositories.EditUserInfoRepository
import java.io.File
import javax.inject.Inject

class EditUserInfoRepositoryImpl @Inject constructor(private val editUserInfoDataSource: EditUserInfoDataSource) :
    EditUserInfoRepository {
    override suspend fun editUserName(name: String?): AuthenticationResponse? {
        return editUserInfoDataSource.editUserName(name)
    }

    override suspend fun editUserImage(image: File?): AuthenticationResponse? {
        return editUserInfoDataSource.editUserImage(image)
    }

}