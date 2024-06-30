package com.graduation.mawruth.ui.islamicmuseum.fragments.discover

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.model.halls.HallsResponse
import com.graduation.domain.useCase.GetAllHallsOfMuseumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IslamicMuseumDiscoverViewModel @Inject constructor(
    private val getAllHallsOfMuseumUseCase: GetAllHallsOfMuseumUseCase
) : ViewModel() {
    val hallList = MutableLiveData<HallsResponse?>()
    val error = MutableLiveData<Boolean>()
    fun getHallsOfMuseum(museumID: Int) {
        viewModelScope.launch {
            try {
                val result = getAllHallsOfMuseumUseCase.invoke(museumID)
                hallList.postValue(result)
            } catch (e: Exception) {
                error.postValue(true)
            }
        }

    }
}