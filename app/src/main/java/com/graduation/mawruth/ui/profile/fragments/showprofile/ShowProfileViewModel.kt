package com.graduation.mawruth.ui.profile.fragments.showprofile

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.graduation.domain.useCase.EditUserImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ShowProfileViewModel @Inject constructor(
    private val editUserImageUseCase: EditUserImageUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    val infoLiveData = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun editUserPhoto(photo: File?) {
        viewModelScope.launch {
            loading.postValue(true)
            try {
                Log.e("user", "before")
                val result = editUserImageUseCase.invoke(photo)
                Log.e("userafter", result.toString())
                val userData = Gson().toJson(result.data?.user)
                Log.e("userafter1", userData)
                sharedPreferences.edit().putString("userData", userData).apply()
                infoLiveData.postValue(true)

            } catch (e: HttpException) {
                Log.e("m", e.localizedMessage.toString())
                infoLiveData.postValue(false)
            } catch (e: Exception) {
                Log.e("m", e.localizedMessage.toString())
            }
        }
    }
}