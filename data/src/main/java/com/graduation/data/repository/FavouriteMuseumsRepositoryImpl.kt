package com.graduation.data.repository

import com.graduation.data.dataSourceContract.FavouriteMuseumsDataSource
import com.graduation.domain.model.Favourite.FavouriteMuseumResponse
import com.graduation.domain.model.FavouriteTest
import com.graduation.domain.model.VerificationResponse
import com.graduation.domain.repositories.FavouriteMuseumsRepository
import javax.inject.Inject

class FavouriteMuseumsRepositoryImpl @Inject constructor(
    private val favouriteMuseumsDataSource: FavouriteMuseumsDataSource
) :FavouriteMuseumsRepository {
    override suspend fun getFavouriteMuseums(): FavouriteMuseumResponse? {
        return favouriteMuseumsDataSource.getFavouriteMuseums()
    }

    override suspend fun sendFavouriteMuseums(museumid: Int): FavouriteTest? {
        return favouriteMuseumsDataSource.sendFavouriteMuseums(museumid)
    }

    override suspend fun deleteFavouriteMuseums(museumid: Int): VerificationResponse? {
        return favouriteMuseumsDataSource.deleteFavouriteMuseums(museumid)

    }
}