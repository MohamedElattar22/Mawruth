package com.graduation.mawruth.ui.resetpassword.cyclefragments

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.model.authenticationuser.User
import com.graduation.domain.useCase.ForgetPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterEmailViewModel
@Inject constructor
    (
    private var sharedPreferences: SharedPreferences,
    private val forgetPasswordUseCase: ForgetPasswordUseCase,
) : ViewModel() {


    val data = MutableLiveData<Boolean>()
    val navigate = MutableLiveData<Boolean>()


    fun verifyEmail(email: String) {
        viewModelScope.launch {
            try {
                val result = forgetPasswordUseCase.invoke(User(email = email))
                if (result?.status == "Success") {
                    navigate.postValue(true)
                    sharedPreferences.edit().putString("email", email).apply()
                }
            } catch (e: Exception) {

                navigate.postValue(false)
                Log.d("OTP", e.localizedMessage!!)
            }
        }

    }

//    fun sendOTPToEmail(email: String) {
//        viewModelScope.launch {
//            try {
//                val result = resendOTPUseCase.invoke(
//                    User(email)
//                )
//                data.postValue(true)
//            } catch (e: Exception) {
//                data.postValue(false)
//            }
//        }
//    }


}