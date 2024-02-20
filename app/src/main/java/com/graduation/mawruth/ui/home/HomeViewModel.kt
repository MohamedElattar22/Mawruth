package com.graduation.mawruth.ui.home

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graduation.domain.model.categories.CategoriesDtoItem
import com.graduation.domain.model.museum.MuseumDto
import com.graduation.domain.model.userinfo.UserInformationDto
import com.graduation.domain.useCase.CategoriesUseCase
import com.graduation.domain.useCase.GetMuseumsDataUseCase
import com.graduation.domain.useCase.GetUserInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMuseumsDataUseCase: GetMuseumsDataUseCase,
    private val categoriesUseCase: CategoriesUseCase,
    private val getUserInformationUseCase: GetUserInformationUseCase,
    private val sharedPreferences: SharedPreferences
) :
    ViewModel() {

    val museumData = MutableLiveData<List<MuseumDto?>?>()
    val museumCategory = MutableLiveData<List<CategoriesDtoItem?>?>()
    val loadingLiveData = MutableLiveData<Boolean>()

    val error = MutableLiveData<String>()
    val infoLiveData = MutableLiveData<UserInformationDto>()

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
                val result = getMuseumsDataUseCase.invoke()
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
//    private suspend fun getUserInfo()
//    {
//        val result = getUserInformationUseCase.invoke()
//        val editor = sharedPreferences.edit()
//        val json = Gson().toJson(result)
//        editor.putString("userInfo", json)
//        editor.apply()
//    }

}