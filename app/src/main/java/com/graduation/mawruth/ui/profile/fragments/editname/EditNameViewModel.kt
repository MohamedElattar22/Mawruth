package com.graduation.mawruth.ui.profile.fragments.editname

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.graduation.domain.useCase.EditUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditNameViewModel @Inject constructor(
    private val editUserInfoUseCase: EditUserInfoUseCase,
    private val sharedPreferences: SharedPreferences
) :
    ViewModel() {

    val infoLiveData = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    fun editName(name: String?) {
        viewModelScope.launch {
            try {
                val result =
                    editUserInfoUseCase.invoke(fullName = name, null, null, null, null, null)
                val json = Gson().toJson(result)
                val editor = sharedPreferences.edit()
                editor.putString("userInfo", json)
                editor.apply()
                infoLiveData.postValue(true)
            } catch (e: Exception) {
                error.postValue(true)
                Log.e("userEdit", e.localizedMessage!!)
            }

        }
    }
}