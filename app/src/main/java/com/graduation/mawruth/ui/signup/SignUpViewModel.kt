package com.graduation.mawruth.ui.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    val userName = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordConfirmation = MutableLiveData<String>()
    val userNameError = MutableLiveData<String?>()
    val emailError = MutableLiveData<String?>()
    val passwordError = MutableLiveData<String?>()
    val passwordConfirmationError = MutableLiveData<String?>()
    val openActivity = MutableLiveData<Boolean>(false)

    fun registerUser() {
        if (!isRegisterValid()) {
            return
        }
        openActivity.postValue(true)
    }

    private fun isRegisterValid(): Boolean {
        var valid = true
        if (userName.value.isNullOrBlank()) {
            //show error
            userNameError.postValue("Enter valid user name")
            valid = false
        } else {
            userNameError.postValue(null)
        }
        if (email.value.isNullOrBlank()) {
            //show error
            emailError.postValue("Enter valid email")
            valid = false

        } else {
            emailError.postValue(null)
        }
        if (password.value.isNullOrBlank()) {
            //show error
            passwordError.postValue("Enter valid password")
            valid = false

        } else {
            passwordError.postValue(null)
        }
        if (passwordConfirmation.value.isNullOrBlank()) {
            //show error
            passwordConfirmationError.postValue("Enter valid password")
            valid = false
        } else {
            passwordConfirmationError.postValue(null)
        }

        return valid
    }
}