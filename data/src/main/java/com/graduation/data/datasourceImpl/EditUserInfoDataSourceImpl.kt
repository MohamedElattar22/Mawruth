package com.graduation.data.datasourceImpl

import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.EditUserInfoDataSource
import com.graduation.domain.model.authenticationuser.AuthenticationResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class EditUserInfoDataSourceImpl @Inject constructor(
    private val webServices: WebServices,
) : EditUserInfoDataSource {
    override suspend fun editUserName(name: String?): AuthenticationResponse? {
        return webServices.updateUserName(name)?.toAuthenticationResponse()
    }

    override suspend fun editUserImage(
        image: File?
    ): AuthenticationResponse? {
        val avatarRB = image?.let {
            MultipartBody.Part.createFormData(
                "image",
                it.name,
                it.asRequestBody("image/*".toMediaTypeOrNull())
            )
        }
        val result = webServices.updateUserImage(avatarRB)

        return result?.toAuthenticationResponse()
    }
}