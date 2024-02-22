package com.graduation.data.datasourceImpl

import android.content.SharedPreferences
import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.EditUserInfoDataSource
import com.graduation.domain.model.userinfo.UserInformationDto
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class EditUserInfoDataSourceImpl @Inject constructor(
    private val webServices: WebServices,
    private val sharedPreferences: SharedPreferences
) : EditUserInfoDataSource {
    override suspend fun editUserInfo(
        fullName: String?,
        userName: String?,
        email: String,
        password: String?,
        phoneNumber: String?,
        avatar: File?
    ): UserInformationDto {
        val fullNameRB = fullName?.toRequestBody("text/plain".toMediaTypeOrNull())
        val userNameRB = userName?.toRequestBody("text/plain".toMediaTypeOrNull())
        val emailRB = email.toRequestBody("text/plain".toMediaTypeOrNull())
        val passwordRB = password?.toRequestBody("text/plain".toMediaTypeOrNull())
        val phoneNumberRB = phoneNumber?.toRequestBody("text/plain".toMediaTypeOrNull())
        val avatarRB = avatar?.let {
            MultipartBody.Part.createFormData(
                "avatar",
                it.name,
                it.asRequestBody("image/*".toMediaTypeOrNull())
            )
        }
        val result = webServices.updateUserData(
            fullNameRB,
            userNameRB,
            emailRB,
            passwordRB,
            phoneNumberRB,
            avatarRB
        )

        return result.toUserInformationDto()
    }
}