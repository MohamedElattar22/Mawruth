package com.graduation.domain.repositories

import com.graduation.domain.model.userinfo.UserInformationDto

interface UserInformationRepository {
    suspend fun getUserInfo(): UserInformationDto?
    suspend fun getUserByEmail(email: String): UserInformationDto
}