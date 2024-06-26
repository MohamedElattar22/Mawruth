package com.graduation.mawruth.ui.home

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.model.authenticationuser.User
import com.graduation.domain.model.categories.CategoriesResponse
import com.graduation.domain.model.museums.MuseumsResponse
import com.graduation.domain.useCase.CategoriesUseCase
import com.graduation.domain.useCase.GetMuseumsAllUseCase
import com.graduation.domain.useCase.GetUserInformationUseCase
import com.graduation.domain.useCase.SendFavouriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMuseumsAllUseCase: GetMuseumsAllUseCase,
    private val categoriesUseCase: CategoriesUseCase,
    private val getUserInformationUseCase: GetUserInformationUseCase,
    private val sharedPreferences: SharedPreferences,
    private val sendFavouriteMuseumUseCase: SendFavouriteUseCase
) :
    ViewModel() {

    val museumData = MutableLiveData<MuseumsResponse?>()
    val museumCategory = MutableLiveData<CategoriesResponse?>()
    val loadingLiveData = MutableLiveData<Boolean>()

    val error = MutableLiveData<String>()
    val infoLiveData = MutableLiveData<User>()

    fun getCategories() {
        viewModelScope.launch {
            try {
                val result = categoriesUseCase.invoke()
                museumCategory.postValue(result)
                Log.e("cat", result.toString())
            } catch (e: Exception) {
                error.postValue(e.localizedMessage)
            }
        }
    }

    fun getMuseumData() {
        viewModelScope.launch {
            loadingLiveData.postValue(true)
            try {
                val result = getMuseumsAllUseCase.invoke()
                museumData.postValue(result)
                Log.e("list", result.toString())
            } catch (e: Exception) {
                error.postValue(e.localizedMessage)
                Log.e("list", e.localizedMessage!!)
            } finally {
                loadingLiveData.postValue(false)
            }
        }
    }
    fun sendfavouritemuseum(museumid:Int){
        viewModelScope.launch {
            loadingLiveData.postValue(true)
            try {
                val result = sendFavouriteMuseumUseCase.invoke(museumid)
                Log.e("postresponse", result?.status!!)
            } catch (e: Exception) {
                error.postValue(e.localizedMessage)
                Log.e("list", e.localizedMessage!!)
            } finally {
                loadingLiveData.postValue(false)
            }
        }
    }
//    private suspend fun getUserInfo()
//    {
//        val result = getUserInformationUseCase.invoke()
//        val editor = sharedPreferences.edit()
//        val json = Gson().toJson(result)
//        editor.putString("userInfo", json)
//        editor.apply()
//    }

}