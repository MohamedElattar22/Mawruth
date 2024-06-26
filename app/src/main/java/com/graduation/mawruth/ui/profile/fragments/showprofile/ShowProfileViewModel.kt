package com.graduation.mawruth.ui.profile.fragments.showprofile

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.graduation.data.model.authuserdata.AuthenticationUserDto
import com.graduation.domain.useCase.EditUserName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ShowProfileViewModel @Inject constructor(
    private val editUserInfoUseCase: EditUserName,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    val infoLiveData = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    private var email: String? = null

    fun editUserPhoto(photo: File?) {
        sharedPreferences.getString("userInfo", null).let {
            email = Gson().fromJson(it, AuthenticationUserDto::class.java).email
        }
        loading.postValue(true)
        viewModelScope.launch {
            try {
//                val result = editUserInfoUseCase.invoke(name = photo)
                val editor = sharedPreferences.edit()
//                val json = Gson().toJson(result)
//                editor.putString("userInfo", json)
                editor.apply()
                infoLiveData.postValue(true)

            } catch (e: Exception) {
                Log.e("photo", e.localizedMessage!!)
                error.postValue(true)
            } finally {
                loading.postValue(false)
            }
        }
    }
}