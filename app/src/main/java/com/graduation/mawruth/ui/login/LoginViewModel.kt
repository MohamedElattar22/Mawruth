package com.graduation.mawruth.ui.login

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.graduation.domain.model.authenticationuser.User
import com.graduation.domain.useCase.GetUserInformationUseCase
import com.graduation.domain.useCase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private var sharedPreferences: SharedPreferences,
    @ApplicationContext val context: Context,
    private val getUserInformationUseCase: GetUserInformationUseCase
) : ViewModel() {

    val loadingLiveData = MutableLiveData<Boolean>()
    val userLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<String?>()
    fun login(email: String, password: String) {
        val user = User(email = email, password = password)
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            try {
                val result = loginUseCase.invoke(user)

                val userData = Gson().toJson(result.data?.user)
                val editor = sharedPreferences.edit()
                editor.putString("userData", userData)
                editor.putString("token", result.data?.token)
                editor.apply()
                userLiveData.postValue(true)
            } catch (e: Exception) {
                errorLiveData.postValue(e.localizedMessage)
            } finally {
                loadingLiveData.postValue(false)
            }
        }
    }

//    private suspend fun getUserInfo() {
//        val result = getUserInformationUseCase.invoke()
//        val json = Gson().toJson(result)
//        val editor = sharedPreferences.edit()
//        editor.putString("userInfo", json)
//        editor.apply()
//    }

}