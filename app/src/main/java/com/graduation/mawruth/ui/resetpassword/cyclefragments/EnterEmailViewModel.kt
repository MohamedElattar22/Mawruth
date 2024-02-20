package com.graduation.mawruth.ui.resetpassword.cyclefragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.model.ResendOtpData
import com.graduation.domain.useCase.GetUserByEmailUseCase
import com.graduation.domain.useCase.ResendOTPUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterEmailViewModel @Inject constructor(
    val resendOTPUseCase: ResendOTPUseCase,
    val getUserByEmailUseCase: GetUserByEmailUseCase
) : ViewModel() {


    val data = MutableLiveData<Boolean>()
    val navigate = MutableLiveData<Boolean>()


//    fun verifyEmail(email: String, otp: String) {
//        viewModelScope.launch {
//            try {
//                val result = ConfirmEmailUseCase.invoke(
//                    EmailConfirmationData(
//                        email,
//                        otp
//                    )
//                )
//                if (result == "OTP verified") {
//                    navigate.postValue(true)
//                }
//            } catch (e: Exception) {
//                errorOcc.postValue(true)
//                navigate.postValue(false)
//                Log.d("OTP", e.localizedMessage!!)
//            }
//        }
//
//    }

    fun sendOTPToEmail(email: String) {
        viewModelScope.launch {
            try {
                val result = resendOTPUseCase.invoke(
                    ResendOtpData(email)
                )
                data.postValue(true)
            } catch (e: Exception) {
                data.postValue(false)
            }
        }
    }


}