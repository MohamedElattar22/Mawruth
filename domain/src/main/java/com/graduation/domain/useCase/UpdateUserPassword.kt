package com.graduation.domain.useCase

import com.graduation.domain.model.PasswordData
import com.graduation.domain.model.authenticationuser.AuthenticationResponse
import com.graduation.domain.repositories.EditUserInfoRepository
import javax.inject.Inject

class UpdateUserPassword @Inject constructor(private val editUserInfoRepository: EditUserInfoRepository) {

    suspend fun invoke(passwordData: PasswordData): AuthenticationResponse? {
        return editUserInfoRepository.editUserPassword(passwordData)
    }
}