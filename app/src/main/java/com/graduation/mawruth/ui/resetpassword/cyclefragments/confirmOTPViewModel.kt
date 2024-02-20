package com.graduation.mawruth.ui.resetpassword.cyclefragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.model.signupdata.EmailConfirmationData
import com.graduation.domain.useCase.ConfirmEmailUseCse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class confirmOTPViewModel @Inject constructor(
    private val confirmEmailUseCase: ConfirmEmailUseCse
) : ViewModel() {
    val errorOcc = MutableLiveData<Boolean>()
    val navigate = MutableLiveData<Boolean>()
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

}