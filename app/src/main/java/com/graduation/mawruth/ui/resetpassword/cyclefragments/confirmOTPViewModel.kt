package com.graduation.mawruth.ui.resetpassword.cyclefragments

import android.content.SharedPreferences
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
class confirmOTPViewModel @Inject constructor(
    private val confirmEmailUseCase: ConfirmEmailUseCse,
    private val resendOTPUseCase: ResendOTPUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    val errorOcc = MutableLiveData<Boolean>()
    val navigate = MutableLiveData<Boolean>()
    val resendStatus = MutableLiveData<Boolean>()
    fun verifyEmail(otp: String) {
        viewModelScope.launch {
            try {
                val result = confirmEmailUseCase.invoke(
                    User(
                        email = sharedPreferences.getString("email", ""),
                        otp = otp
                    )
                )
                if (result.status.toString() == "Success") {
                    navigate.postValue(true)
                }


            } catch (e: Exception) {
                errorOcc.postValue(true)
                navigate.postValue(false)
                Log.d("OTP", e.localizedMessage!!)
            }
        }

    }

    fun sendOTPToEmail() {
        viewModelScope.launch {
            try {
                val result = resendOTPUseCase.invoke(
                    User(email = sharedPreferences.getString("email", ""))
                )
                if (result?.status == "Success") {
                    resendStatus.postValue(true)
                }

            } catch (e: Exception) {
                resendStatus.postValue(false)
            }
        }
    }
}