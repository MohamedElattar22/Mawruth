package com.graduation.mawruth.ui.splash

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.useCase.GetUserInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUserInformationUseCase: GetUserInformationUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    val infoLiveData = MutableLiveData<Boolean>()
    fun getUserInfo() {
        viewModelScope.launch {
            try {
                if (sharedPreferences.contains("userData")) {
                    sharedPreferences.getString("userData", null)?.let {
                        infoLiveData.postValue(true)
                    }

                } else {
                    infoLiveData.postValue(false)
                }

            } catch (e: Exception) {
                Log.e("userInfo", e.localizedMessage!!)
            }
        }
    }
}