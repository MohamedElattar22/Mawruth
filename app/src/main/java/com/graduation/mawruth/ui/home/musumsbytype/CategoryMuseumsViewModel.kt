package com.graduation.mawruth.ui.home.musumsbytype

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.model.museums.MuseumsResponse
import com.graduation.domain.useCase.GetMuseumsAllUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryMuseumsViewModel @Inject constructor(
    private val getMuseumsByTypeUseCase: GetMuseumsAllUseCase
) : ViewModel() {
    val museumData = MutableLiveData<MuseumsResponse?>()
    val isLoading = MutableLiveData<Boolean>() // observer
    val error = MutableLiveData<String>()
    fun getMuseumByType(category: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val res = getMuseumsByTypeUseCase.invoke(name = category)
                museumData.postValue(res)
            } catch (e: Exception) {
                error.postValue(e.localizedMessage)
            } finally {
                isLoading.postValue(false)
            }
        }
    }

}