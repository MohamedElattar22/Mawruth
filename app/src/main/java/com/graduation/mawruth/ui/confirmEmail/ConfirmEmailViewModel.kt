package com.graduation.mawruth.ui.confirmEmail

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
class ConfirmEmailViewModel
@Inject constructor(private val ConfirmEmailUseCase: ConfirmEmailUseCse) : ViewModel() {

    val errorOcc = MutableLiveData<Boolean>()
    val navigate = MutableLiveData<Boolean>()


    fun verifyEmail(email: String, otp: String) {
        viewModelScope.launch {
            try {
                val result = ConfirmEmailUseCase.invoke(
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