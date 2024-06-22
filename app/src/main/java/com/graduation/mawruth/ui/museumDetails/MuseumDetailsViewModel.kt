package com.graduation.mawruth.ui.museumDetails

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.graduation.domain.model.authenticationuser.User
import com.graduation.domain.model.museums.MuseumItem
import com.graduation.domain.model.reviews.AllReviewsResponse
import com.graduation.domain.model.reviews.ReviewsData
import com.graduation.domain.model.reviews.ReviewsResponse
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
    private val getReviewsUseCase: GetReviewsUseCase,
) : ViewModel() {
    val error = MutableLiveData<String>()
    val infoLiveData = MutableLiveData<MuseumItem?>()
    val reviewLiveData = MutableLiveData<ReviewsResponse?>()
    val reviewListLiveData = MutableLiveData<AllReviewsResponse?>()
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
        var reviewDto: ReviewsData? = null
        sharedPreferences.getString("userInfo", null)?.let {
            val user = Gson().fromJson(it, User::class.java)
            reviewDto =
                ReviewsData(userId = user.id, museumId = museumId, rating = 5, content = review)
        }
        viewModelScope.launch {
            try {
                val result = sendReviewUseCase.invoke(museumId, reviewDto!!)
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