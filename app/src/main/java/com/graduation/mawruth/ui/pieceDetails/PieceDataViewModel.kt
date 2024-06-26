package com.graduation.mawruth.ui.pieceDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.model.pieces.PiecesItem
import com.graduation.domain.useCase.GetPieceByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PieceDataViewModel @Inject constructor(
    private val getPieceByIdUseCase: GetPieceByIdUseCase
) : ViewModel() {

    val pieceData = MutableLiveData<PiecesItem?>()
    val error = MutableLiveData<String>()
    fun getPieceById(pieceId: Int) {
        viewModelScope.launch {
            try {
                val result = getPieceByIdUseCase.invoke(pieceId)
                pieceData.postValue(result)

            } catch (e: Exception) {
                error.postValue(e.localizedMessage)
            }
        }
    }
}