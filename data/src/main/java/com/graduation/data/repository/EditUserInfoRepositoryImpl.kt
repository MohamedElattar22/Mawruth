package com.graduation.data.repository

import android.util.Log
import com.graduation.data.dataSourceContract.EditUserInfoDataSource
import com.graduation.domain.model.PasswordData
import com.graduation.domain.model.authenticationuser.AuthenticationResponse
import com.graduation.domain.repositories.EditUserInfoRepository
import java.io.File
import javax.inject.Inject

class EditUserInfoRepositoryImpl @Inject constructor(private val editUserInfoDataSource: EditUserInfoDataSource) :
    EditUserInfoRepository {
    override suspend fun editUserName(name: String?): AuthenticationResponse? {
        return editUserInfoDataSource.editUserName(name)
    }

    override suspend fun editUserImage(image: File?): AuthenticationResponse {
        Log.e("userRepo", editUserInfoDataSource.editUserImage(image).toString())
        return editUserInfoDataSource.editUserImage(image)
    }

    override suspend fun editUserPassword(passwordData: PasswordData): AuthenticationResponse? {
        return editUserInfoDataSource.editUserPassword(passwordData)
    }

}