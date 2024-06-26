package com.graduation.domain.repositories

import com.graduation.domain.model.authenticationuser.AuthenticationResponse
import java.io.File

interface EditUserInfoRepository {
    suspend fun editUserName(
        name: String?,
    ): AuthenticationResponse?

    suspend fun editUserImage(
        image: File?,
    ): AuthenticationResponse?
}