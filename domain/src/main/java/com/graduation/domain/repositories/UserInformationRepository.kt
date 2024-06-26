package com.graduation.domain.repositories

import com.graduation.domain.model.authenticationuser.AuthenticationResponse

interface UserInformationRepository {
    suspend fun getUserInfo(): AuthenticationResponse?
}