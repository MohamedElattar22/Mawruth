package com.graduation.mawruth.ui.favourities

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.model.museums.MuseumsResponse
import com.graduation.domain.useCase.FavouriteMuseumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel
@Inject constructor(private val favouriteMuseumsUseCase: FavouriteMuseumsUseCase) : ViewModel() {

    val museumData = MutableLiveData<MuseumsResponse?>()
    fun getMuseumData() {
        viewModelScope.launch {
            try {

                val result = favouriteMuseumsUseCase.getFavouriteMuseums()
                museumData.postValue(result)
                result?.status?.let { Log.e("status", it) }

            } catch (e: Exception) {

                Log.e("list", e.localizedMessage!!)
            }
        }
    }
}