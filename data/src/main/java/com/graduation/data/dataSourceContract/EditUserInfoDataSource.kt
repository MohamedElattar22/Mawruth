package com.graduation.data.dataSourceContract

import com.graduation.domain.model.PasswordData
import com.graduation.domain.model.authenticationuser.AuthenticationResponse
import java.io.File

interface EditUserInfoDataSource {
    suspend fun editUserName(
        name: String?
    ): AuthenticationResponse?

    suspend fun editUserImage(
        image: File?
    ): AuthenticationResponse

    suspend fun editUserPassword(
        passwordData: PasswordData
    ): AuthenticationResponse?
}