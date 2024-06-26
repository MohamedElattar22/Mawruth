package com.graduation.mawruth.ui.resetpassword

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.model.authenticationuser.User
import com.graduation.domain.useCase.ResetPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val resetPasswordUseCase: ResetPasswordUseCase,
) : ViewModel() {

    val infoLiveData = MutableLiveData<Boolean>()
    fun editPassword(email: String, password: String) {

        viewModelScope.launch {
            try {
                val res =
                    resetPasswordUseCase.invoke(User(email = email), User(password = password))
                infoLiveData.postValue(true)
            } catch (e: Exception) {
                Log.e("userInfo", e.localizedMessage!!)
                infoLiveData.postValue(false)
            }
        }

    }

}