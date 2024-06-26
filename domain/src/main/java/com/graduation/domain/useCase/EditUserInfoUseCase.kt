package com.graduation.domain.useCase

import com.graduation.domain.model.authenticationuser.AuthenticationResponse
import com.graduation.domain.repositories.EditUserInfoRepository
import javax.inject.Inject

class EditUserName @Inject constructor
    (private val editUserInfoRepository: EditUserInfoRepository) {
    suspend fun invoke(
        name: String?,
    ): AuthenticationResponse? {
        val result = editUserInfoRepository.editUserName(
            name,
        )
        return result
    }
}