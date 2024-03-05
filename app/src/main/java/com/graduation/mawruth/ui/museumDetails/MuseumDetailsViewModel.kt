package com.graduation.mawruth.ui.museumDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.model.museumdata.MuseumDataDto
import com.graduation.domain.useCase.GetMuseumByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MuseumDetailsViewModel @Inject constructor(
    private val getMuseumByIDUseCase: GetMuseumByIdUseCase
) : ViewModel() {
    val error = MutableLiveData<String>()
    val infoLiveData = MutableLiveData<MuseumDataDto?>()
    fun getMuseumById(museumId: Int) {
        viewModelScope.launch {
            try {
                val result = getMuseumByIDUseCase.invoke(museumId)
                infoLiveData.postValue(result)
            } catch (e: Exception) {
                error.postValue(e.localizedMessage)
            }

        }
    }

}