package com.graduation.mawruth.ui.museumDetails

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.graduation.domain.model.ReviewDto
import com.graduation.domain.model.museumdata.MuseumDataDto
import com.graduation.domain.model.userinfo.UserInformationDto
import com.graduation.domain.useCase.GetMuseumByIdUseCase
import com.graduation.domain.useCase.GetReviewsUseCase
import com.graduation.domain.useCase.SendReviewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MuseumDetailsViewModel @Inject constructor(
    private val getMuseumByIDUseCase: GetMuseumByIdUseCase,
    private val sendReviewUseCase: SendReviewUseCase,
    private val sharedPreferences: SharedPreferences,
    private val getReviewsUseCase: GetReviewsUseCase
) : ViewModel() {
    val error = MutableLiveData<String>()
    val infoLiveData = MutableLiveData<MuseumDataDto?>()
    val reviewLiveData = MutableLiveData<ReviewDto?>()
    val reviewListLiveData = MutableLiveData<List<ReviewDto?>?>()
    fun getMuseumById(museumId: Int) {
        viewModelScope.launch {
            try {
                val result = getMuseumByIDUseCase.invoke(museumId)
                infoLiveData.postValue(result)
            } catch (e: Exception) {
                error.postValue(e.localizedMessage)
            }

        }
    }

    fun sendReview(review: String, museumId: Int) {
        var reviewDto: ReviewDto? = null
        sharedPreferences.getString("userInfo", null)?.let {
            val user = Gson().fromJson(it, UserInformationDto::class.java)
            reviewDto =
                ReviewDto(userId = user.iD, museumId = museumId, rating = 5.0, content = review)
        }
        viewModelScope.launch {
            try {
                val result = sendReviewUseCase.invoke(reviewDto!!)
                reviewLiveData.postValue(result)
            } catch (e: Exception) {
                error.postValue(e.localizedMessage)
            }
        }
    }

    fun getReviews(museumId: Int) {
        viewModelScope.launch {
            try {
                val result = getReviewsUseCase.invoke(museumId)
                reviewListLiveData.postValue(result)
            } catch (e: Exception) {
                error.postValue(e.localizedMessage)
            }
        }
    }

}