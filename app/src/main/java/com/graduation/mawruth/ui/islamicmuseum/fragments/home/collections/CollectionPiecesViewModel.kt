package com.graduation.mawruth.ui.islamicmuseum.fragments.home.collections

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.model.pieces.PiecesResponse
import com.graduation.domain.useCase.GetPiecesOfCollectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionPiecesViewModel @Inject constructor(
    private var getPiecesOfCollectionUseCase: GetPiecesOfCollectionUseCase
) : ViewModel() {
    val loading = MutableLiveData<Boolean>()
    val piecesList = MutableLiveData<PiecesResponse?>()
    val error = MutableLiveData<Boolean>()
    fun getPiecesOfCollection(museumID: Int, CollectionID: Int) {
        loading.postValue(true)
        viewModelScope.launch {
            try {
                val result = getPiecesOfCollectionUseCase.invoke(
                    museumID = museumID,
                    collectionID = CollectionID
                )

                piecesList.postValue(result)
            } catch (e: Exception) {
                loading.postValue(false)
                error.postValue(true)
            } finally {
                loading.postValue(false)
            }
        }
    }

}