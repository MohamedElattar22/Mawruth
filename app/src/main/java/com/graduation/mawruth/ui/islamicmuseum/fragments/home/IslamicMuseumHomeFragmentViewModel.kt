package com.graduation.mawruth.ui.islamicmuseum.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.model.halls.HallsResponse
import com.graduation.domain.model.stories.StoriesResponse
import com.graduation.domain.useCase.GetAllHallsOfMuseumUseCase
import com.graduation.domain.useCase.GetAllStoriesOfMuseumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IslamicMuseumHomeFragmentViewModel @Inject constructor(
    private val getAllStoriesOfMuseumUseCase: GetAllStoriesOfMuseumUseCase,
    private val getAllHallsOfMuseumUseCase: GetAllHallsOfMuseumUseCase
) : ViewModel() {

    val storiesList = MutableLiveData<StoriesResponse>()
    val hallsList = MutableLiveData<HallsResponse>()
    val error = MutableLiveData<String>()
    fun getStories(museumId: Int) {
        viewModelScope.launch {
            try {
                val result = getAllStoriesOfMuseumUseCase.invoke(museumId)
                storiesList.postValue(result)
            } catch (e: Exception) {
                error.postValue(e.localizedMessage)
            }
        }
    }

    fun getHalls(museumId: Int) {
        viewModelScope.launch {
            try {
                val result = getAllHallsOfMuseumUseCase.invoke(museumId)
                hallsList.postValue(result)
            } catch (e: Exception) {
                error.postValue(e.localizedMessage)
            }
        }
    }

}