package com.graduation.mawruth.ui.stories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.model.stories.StoriesResponse
import com.graduation.domain.useCase.GetAllStoriesOfMuseumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoriesViewModel @Inject constructor(
    private val getAllStoriesOfMuseumUseCase: GetAllStoriesOfMuseumUseCase
) : ViewModel() {
    val storiesList = MutableLiveData<StoriesResponse?>()
    val error = MutableLiveData<Boolean>()
    fun getAllStories(museumID: Int) {
        viewModelScope.launch {
            try {
                val result = getAllStoriesOfMuseumUseCase.invoke(museumID)
                storiesList.postValue(result)
            } catch (e: Exception) {
                error.postValue(true)
            }
        }
    }
}