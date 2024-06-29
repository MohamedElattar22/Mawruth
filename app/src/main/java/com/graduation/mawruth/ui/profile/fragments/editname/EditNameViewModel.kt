package com.graduation.mawruth.ui.profile.fragments.editname

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.graduation.domain.useCase.EditUserName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditNameViewModel @Inject constructor(
    private val editUserInfoUseCase: EditUserName,
    private val sharedPreferences: SharedPreferences
) :
    ViewModel() {

    val infoLiveData = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    fun editName(name: String?) {
        viewModelScope.launch {
            try {
                val result = editUserInfoUseCase.invoke(name)
                val json = Gson().toJson(result?.data?.user)
                val editor = sharedPreferences.edit()
                editor.putString("userData", json)
                editor.apply()
                infoLiveData.postValue(true)
            } catch (e: Exception) {
                error.postValue(true)
                Log.e("userEdit", e.localizedMessage!!)
            }

        }
    }
}