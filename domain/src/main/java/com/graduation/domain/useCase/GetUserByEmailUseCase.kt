package com.graduation.domain.useCase

import com.graduation.domain.model.userinfo.UserInformationDto
import com.graduation.domain.repositories.UserInformationRepository
import javax.inject.Inject

class GetUserByEmailUseCase @Inject constructor
    (val userInformationRepository: UserInformationRepository) {
    suspend fun invoke(email: String): UserInformationDto {
        val result = userInformationRepository.getUserByEmail(email)
        return result
    }

}