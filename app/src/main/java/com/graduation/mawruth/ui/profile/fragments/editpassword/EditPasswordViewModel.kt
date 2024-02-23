package com.graduation.mawruth.ui.profile.fragments.editpassword

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.graduation.domain.model.userinfo.UserInformationDto
import com.graduation.domain.useCase.EditUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditPasswordViewModel @Inject constructor(
    private val editUserInfoUseCase: EditUserInfoUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    val infoLiveData = MutableLiveData<Boolean>()
    private var email: String? = null


    fun editPassword(newPassword: String) {
        sharedPreferences.getString("userInfo", null).let {
            email = Gson().fromJson(it, UserInformationDto::class.java).email
        }
        viewModelScope.launch {
            try {
                val result =
                    editUserInfoUseCase.invoke(null, null, email!!, newPassword, null, null)
                val json = Gson().toJson(result)
                val editor = sharedPreferences.edit()
                editor.putString("userInfo", json)
                editor.apply()
                infoLiveData.postValue(true)
            } catch (e: Exception) {
                Log.e("userInfo", e.localizedMessage!!)
                infoLiveData.postValue(false)
            }
        }

    }

}