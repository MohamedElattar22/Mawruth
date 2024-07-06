package com.graduation.mawruth.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.model.museums.MuseumsResponse
import com.graduation.domain.useCase.GetMuseumsAllUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getMuseumsByNameUseCase: GetMuseumsAllUseCase
) : ViewModel() {
    val museumList = MutableLiveData<MuseumsResponse?>()
    val museumName = MutableLiveData<String>()
    val error = MutableLiveData<Boolean>()
    fun searchOnMuseumsByName() {
        viewModelScope.launch {
            try {
                val result = getMuseumsByNameUseCase.invoke(name = museumName.value.toString())
                museumList.postValue(result)
            } catch (e: Exception) {
                error.postValue(true)
                Log.d("errorSearch", e.message.toString())
            }
        }
    }
}