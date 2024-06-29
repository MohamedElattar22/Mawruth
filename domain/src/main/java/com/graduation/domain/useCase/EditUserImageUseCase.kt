package com.graduation.domain.useCase

import android.util.Log
import com.graduation.domain.model.authenticationuser.AuthenticationResponse
import com.graduation.domain.repositories.EditUserInfoRepository
import java.io.File
import javax.inject.Inject

class EditUserImageUseCase @Inject constructor(private val editUserInfoRepository: EditUserInfoRepository) {

    suspend fun invoke(image: File?): AuthenticationResponse {
        Log.e("userRepo", editUserInfoRepository.editUserImage(image).toString())
        return editUserInfoRepository.editUserImage(image)
    }
}