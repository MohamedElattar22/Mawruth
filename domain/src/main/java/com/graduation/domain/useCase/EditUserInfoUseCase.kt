package com.graduation.domain.useCase

import com.graduation.domain.model.userinfo.UserInformationDto
import com.graduation.domain.repositories.EditUserInfoRepository
import java.io.File
import javax.inject.Inject

class EditUserInfoUseCase @Inject constructor(private val editUserInfoRepository: EditUserInfoRepository) {
    suspend fun invoke(
        fullName: String?,
        userName: String?,
        email: String?,
        password: String?,
        phoneNumber: String?,
        avatar: File?
    ): UserInformationDto? {
        val result = editUserInfoRepository.editUserInfo(
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