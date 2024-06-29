package com.graduation.mawruth.ui.profile.fragments.editpassword

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.model.PasswordData
import com.graduation.domain.useCase.UpdateUserPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditPasswordViewModel @Inject constructor(
    private val updateUserPassword: UpdateUserPassword,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    val infoLiveData = MutableLiveData<Boolean>()
    private var email: String? = null


    fun editPassword(newPassword: String, currentPassword: String) {
        val data = PasswordData(currentPassword, newPassword)
        viewModelScope.launch {
            try {
                val result = updateUserPassword.invoke(data)
                val editor = sharedPreferences.edit()
                editor.putString("token", result?.data?.token)
                editor.apply()
                infoLiveData.postValue(true)
            } catch (e: Exception) {
                infoLiveData.postValue(false)
            }
        }

    }

}