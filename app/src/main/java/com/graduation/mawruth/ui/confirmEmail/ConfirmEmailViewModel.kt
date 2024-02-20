package com.graduation.mawruth.ui.confirmEmail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.model.ResendOtpData
import com.graduation.domain.model.signupdata.EmailConfirmationData
import com.graduation.domain.useCase.ConfirmEmailUseCse
import com.graduation.domain.useCase.GetUserByEmailUseCase
import com.graduation.domain.useCase.ResendOTPUseCase
import com.graduation.mawruth.ui.resetpassword.cyclefragments.emailProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmEmailViewModel
@Inject constructor(
    private val confirmEmailUseCase: ConfirmEmailUseCse,
    private val resendOTPUseCase: ResendOTPUseCase,
    private val getUserByEmailUseCase: GetUserByEmailUseCase
) : ViewModel() {

    val errorOcc = MutableLiveData<Boolean>()
    val navigate = MutableLiveData<Boolean>()
    val resendStatus = MutableLiveData<Boolean>()
    val getEmailLiveData = MutableLiveData<Boolean>()


    fun verifyEmail(email: String, otp: String) {
        viewModelScope.launch {
            try {
                val result = confirmEmailUseCase.invoke(
                    EmailConfirmationData(
                        email,
                        otp
                    )
                )
                if (result == "OTP verified") {
                    navigate.postValue(true)
                }
            } catch (e: Exception) {
                errorOcc.postValue(true)
                navigate.postValue(false)
                Log.d("OTP", e.localizedMessage!!)
            }
        }

    }

    fun sendOTPToEmail(email: String) {
        viewModelScope.launch {
            try {
                val result = resendOTPUseCase.invoke(
                    ResendOtpData(email)
                )
                resendStatus.postValue(true)
            } catch (e: Exception) {
                resendStatus.postValue(false)
            }
        }
    }

    fun getUserByEmail(email: String) {
        viewModelScope.launch {
            try {
                val result = getUserByEmailUseCase.invoke(
                    email
                )
                emailProvider.accountData = result
                getEmailLiveData.postValue(true)
            } catch (e: Exception) {
                getEmailLiveData.postValue(false)
            }
        }

    }


}