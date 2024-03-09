package com.graduation.mawruth.ui.home.musumsbytype

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.graduation.domain.model.museumdata.MuseumDataDto
import com.graduation.domain.useCase.GetMuseumsByTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryMuseumsViewModel @Inject constructor(
    private val getMuseumsByTypeUseCase: GetMuseumsByTypeUseCase
) : ViewModel() {
    val museumData = MutableLiveData<List<MuseumDataDto?>?>()
    val isLoading = MutableLiveData<Boolean>() // observer
    val error = MutableLiveData<String>()
    fun getMuseumByType(typeId: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val res = getMuseumsByTypeUseCase.invoke(typeId)
                museumData.postValue(res)
                isLoading.postValue(false)
            } catch (e: Exception) {
                error.postValue(e.localizedMessage)
                isLoading.postValue(false)
            } finally {
                isLoading.postValue(false)
            }
        }
    }

}