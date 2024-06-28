package com.graduation.mawruth.ui.halls


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.model.halls.HallsResponse
import com.graduation.domain.useCase.GetHallByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class hallsViewModel @Inject constructor(
    private val getHallByIdUseCase: GetHallByIdUseCase
) : ViewModel() {

    val hallList = MutableLiveData<HallsResponse>()
    val error = MutableLiveData<String>()

    fun getHallByID(hallID: Int) {
        viewModelScope.launch {
            try {
                val result = getHallByIdUseCase.invoke(hallID)
                hallList.postValue(result)
            } catch (e: Exception) {
                error.postValue(e.localizedMessage)
            }
        }
    }

}