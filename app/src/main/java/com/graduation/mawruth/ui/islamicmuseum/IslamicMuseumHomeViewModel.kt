package com.graduation.mawruth.ui.islamicmuseum

import androidx.lifecycle.ViewModel
import com.graduation.domain.useCase.GetAllHallsOfMuseumUseCase
import com.graduation.domain.useCase.GetAllStoriesOfMuseumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IslamicMuseumHomeViewModel @Inject constructor(
    private val getAllStoriesOfMuseumUseCase: GetAllStoriesOfMuseumUseCase,
    private val getAllHallsOfMuseumUseCase: GetAllHallsOfMuseumUseCase
) :
    ViewModel()