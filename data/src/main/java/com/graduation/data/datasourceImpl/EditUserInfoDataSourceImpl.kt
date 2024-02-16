package com.graduation.data.datasourceImpl

import android.content.SharedPreferences
import com.google.gson.Gson
import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.EditUserInfoDataSource
import com.graduation.domain.model.userinfo.UserInformationDto
import com.graduation.domain.model.userlogin.UserLoginDto
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
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
        email: String?,
        password: String?,
        phoneNumber: String?,
        avatar: File?
    ): UserInformationDto? {
        val result = sharedPreferences.getString("userData", null)?.let { user ->
            val userId = Gson().fromJson(user, UserLoginDto::class.java).id
            val fullNameRB = fullName?.let {
                it.toRequestBody("text/plain".toMediaTypeOrNull())
            }
            val userNameRB = userName?.let {
                it.toRequestBody("text/plain".toMediaTypeOrNull())
            }
            val emailRB = email?.let {
                it.toRequestBody("text/plain".toMediaTypeOrNull())
            }

            val passwordRB = password?.let {
                it.toRequestBody("text/plain".toMediaTypeOrNull())
            }
            val phoneNumberRB = phoneNumber?.let {
                it.toRequestBody("text/plain".toMediaTypeOrNull())
            }
            val avatarRB = avatar?.let {
                MultipartBody.Part.createFormData(
                    "avatar",
                    it.name,
                    it.asRequestBody("image/*".toMediaTypeOrNull())
                )
            }
            webServices.updateUserData(
                userId!!.toInt(),
                fullNameRB,
                userNameRB,
                emailRB,
                passwordRB,
                phoneNumberRB,
                avatarRB
            )

        }
        return result?.toUserInformationDto()
    }
}