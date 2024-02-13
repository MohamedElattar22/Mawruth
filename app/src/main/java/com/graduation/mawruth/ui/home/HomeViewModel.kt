package com.graduation.mawruth.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.model.museum.MuseumDto
import com.graduation.domain.useCase.GetMuseumsDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getMuseumsDataUseCase: GetMuseumsDataUseCase) :
    ViewModel() {

    val museumData = MutableLiveData<List<MuseumDto?>?>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun getMuseumData() {
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            try {
                val result = getMuseumsDataUseCase.invoke()
                museumData.postValue(result)
                Log.e("list", result.toString())
            } catch (e: Exception) {
                error.postValue(e.localizedMessage)
                Log.e("list", e.localizedMessage!!)
            } finally {
                loadingLiveData.postValue(false)
            }
        }
    }
}