package com.graduation.mawruth.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.graduation.domain.model.userlogin.UserLoginPost
import com.graduation.domain.useCase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    val loadingLiveData = MutableLiveData<Boolean>()
    val userLiveData = MutableLiveData<String>()
    val errorLiveData = MutableLiveData<String?>()
    fun login(email: String, password: String) {
        val user = UserLoginPost(email, password)
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            try {
                val result = loginUseCase.invoke(user)
//                SessionProvider.user = result
                val json = Gson().toJson(result)
                userLiveData.postValue(json)
            } catch (e: Exception) {
                errorLiveData.postValue(e.localizedMessage)
            } finally {
                loadingLiveData.postValue(false)
            }
        }
    }
}