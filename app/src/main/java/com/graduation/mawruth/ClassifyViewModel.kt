package com.graduation.mawruth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.useCase.ModelAiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ClassifyViewModel @Inject constructor(
    private val modelAiUseCase: ModelAiUseCase
) : ViewModel() {
    val responseLiveData = MutableLiveData<String?>()
    val errorLiveData = MutableLiveData<Boolean?>()

    fun getPrediction(image: File) {
        viewModelScope.launch {
            try {
                val result = modelAiUseCase.invoke(image)
                responseLiveData.postValue(result?.result.toString())
                Log.e("model", result?.result.toString())
            } catch (e: Exception) {
                errorLiveData.postValue(true)
                Log.e("model", e.message.toString())
            }
        }
    }
}