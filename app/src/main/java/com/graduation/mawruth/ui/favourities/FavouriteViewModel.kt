package com.graduation.mawruth.ui.favourities

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.model.Favourite.FavouriteMuseumResponse
import com.graduation.domain.model.Favourite.FavouritePieceResponse
import com.graduation.domain.useCase.DeleteFavouriteMuseumUseCase
import com.graduation.domain.useCase.FavouriteMuseumsUseCase
import com.graduation.domain.useCase.getFavouritepiecesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class FavouriteViewModel
@Inject constructor(
    private val favouriteMuseumsUseCase: FavouriteMuseumsUseCase,
    private val deleteFavouriteUseCase: DeleteFavouriteMuseumUseCase,
    private val getFavouritepiecesUseCase: getFavouritepiecesUseCase
  //  private val deleteFavouritePiecesUseCase: deleteFavouritePiecesUseCase
    )
    : ViewModel() {

    val museumData = MutableLiveData<FavouriteMuseumResponse?>()
    val piecedata = MutableLiveData<FavouritePieceResponse?>()
    fun getPiecesData(){
        viewModelScope.launch {
            try {

                val result = getFavouritepiecesUseCase.getFavouritePieces()
                piecedata.postValue(result)
                result?.data?.let { Log.e("status", it.toString()) }

            } catch (e: Exception) {

                Log.e("list", e.localizedMessage!!)
            }
        }
    }
    fun getMuseumData() {
        viewModelScope.launch {
            try {

                val result = favouriteMuseumsUseCase.getFavouriteMuseums()
                museumData.postValue(result)
                result?.data?.let { Log.e("status", it.toString()) }

            } catch (e: Exception) {

                Log.e("list", e.localizedMessage!!)
            }
        }
    }
    fun deleteMuseumData(museumid : Int) {
        viewModelScope.launch {
            try {
                val result = deleteFavouriteUseCase.invoke(museumid)
                Log.e("deleteresponse", result?.message!!)
            } catch (e: Exception) {
                Log.e("list", e.localizedMessage!!)
            }
        }
    }
  /*  fun deletepiecesData(museumid : Int) {
        viewModelScope.launch {
            try {
                val result = deleteFavouritePiecesUseCase.invoke(museumid)
                Log.e("deleteresponse", result?.message!!)
            } catch (e: Exception) {
                Log.e("list", e.localizedMessage!!)
            }
        }
    }*/



}