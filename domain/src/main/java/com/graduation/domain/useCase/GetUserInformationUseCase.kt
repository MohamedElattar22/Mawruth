package com.graduation.domain.useCase

import com.graduation.domain.model.userinfo.UserInformationDto
import com.graduation.domain.repositories.UserInformationRepository
import javax.inject.Inject

class GetUserInformationUseCase @Inject constructor(private val userInformationRepository: UserInformationRepository) {
    suspend fun invoke(): UserInformationDto? {
        return userInformationRepository.getUserInfo()
    }
}