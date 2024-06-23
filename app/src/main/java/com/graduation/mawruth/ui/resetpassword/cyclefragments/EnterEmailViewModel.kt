package com.graduation.mawruth.ui.resetpassword.cyclefragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.graduation.domain.model.authenticationuser.User
import com.graduation.domain.useCase.ConfirmEmailUseCse

import com.graduation.domain.useCase.ResendOTPUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterEmailViewModel @Inject constructor(
    val resendOTPUseCase: ResendOTPUseCase,
    val confirmEmailUseCase: ConfirmEmailUseCse
) : ViewModel() {


    val data = MutableLiveData<Boolean>()
    val navigate = MutableLiveData<Boolean>()


    fun verifyEmail(email: String, otp: String) {
        viewModelScope.launch {
            try {
                val result = confirmEmailUseCase.invoke(
                    User(email)
                )
                if (result.status == "Success") {
                    navigate.postValue(true)
                }
            } catch (e: Exception) {

                navigate.postValue(false)
                Log.d("OTP", e.localizedMessage!!)
            }
        }

    }

    fun sendOTPToEmail(email: String) {
        viewModelScope.launch {
            try {
                val result = resendOTPUseCase.invoke(
                    User(email)
                )
                data.postValue(true)
            } catch (e: Exception) {
                data.postValue(false)
            }
        }
    }


}