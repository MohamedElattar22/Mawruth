package com.graduation.data.datasourceImpl

import android.content.SharedPreferences
import com.google.gson.Gson
import com.graduation.data.api.WebServices
import com.graduation.data.dataSourceContract.UserInformationDataSource
import com.graduation.domain.model.userinfo.UserInformationDto
import com.graduation.domain.model.userlogin.UserLoginDto
import javax.inject.Inject

class UserInformationDataSourceImpl @Inject constructor(
    private val webServices: WebServices, private val sharedPreferences: SharedPreferences
) : UserInformationDataSource {
    override suspend fun getUserInfo(): UserInformationDto? {
        val result = sharedPreferences.getString("userData", null)?.let {
            val user = Gson().fromJson(it, UserLoginDto::class.java)
            webServices.getUserInfo(user?.id!!).toUserInformationDto()
        }
        return result
    }
}