package com.graduation.mawruth.ui.signup


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.model.signupdata.SignUpRequiredData
import com.graduation.domain.useCase.SignUpUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUserUseCase: SignUpUserUseCase
) : ViewModel() {

    val userName = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordConfirmation = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()
    val openActivity = MutableLiveData<Boolean>(false)
    val loading = MutableLiveData<Boolean>()


    fun registerUser() {
        loading.postValue(true)
        viewModelScope.launch {
            try {
                signUpUserUseCase.invoke(
                    SignUpRequiredData(
                        full_name = null,
                        email = email.value,
                        user_name = userName.value,
                        password = password.value
                    )
                )

                openActivity.postValue(true)


            } catch (e: Exception) {
                errorMessage.postValue(e.localizedMessage)
                openActivity.postValue(false)

            } finally {
                loading.postValue(false)
            }

        }


    }

}