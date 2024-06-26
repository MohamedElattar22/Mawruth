package com.graduation.data.repository


import com.graduation.domain.repositories.AIModelRepository
import com.graduation.domain.repositories.CategoriesRepository
import com.graduation.domain.repositories.CollectionsRepository
import com.graduation.domain.repositories.EditUserInfoRepository
import com.graduation.domain.repositories.FavouriteMuseumsRepository
import com.graduation.domain.repositories.FavouritePieceRepository
import com.graduation.domain.repositories.HallRepository
import com.graduation.domain.repositories.MuseumRepository
import com.graduation.domain.repositories.PiecesRepository
import com.graduation.domain.repositories.ReviewsRepository
import com.graduation.domain.repositories.StoriesRepository
import com.graduation.domain.repositories.UserAuthenticationRepository
import com.graduation.domain.repositories.UserInformationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class di {

    @Binds
    abstract fun bindSignUpRepo(
        repository: UserAuthenticationRepositoryImpl
    ): UserAuthenticationRepository

    @Binds
    abstract fun bindMuseumRepository(museumRepositoryImpl: MuseumRepositoryImpl): MuseumRepository

    @Binds
    abstract fun bindUserInfoRepositoryRepository(userInformationRepositoryImpl: UserInformationRepositoryImpl): UserInformationRepository

    @Binds
    abstract fun bindEditUserInfoRepositoryRepository(editUserInfoRepositoryImpl: EditUserInfoRepositoryImpl): EditUserInfoRepository

    @Binds
    abstract fun bindCategories(categoriesRepositoryImpl: CategoriesRepositoryImpl): CategoriesRepository

    @Binds
    abstract fun bindReviewsRepository(reviewsRepositoryImpl: ReviewsRepositoryImpl): ReviewsRepository

    @Binds
    abstract fun bindPiecesRepository(piecesRepositoryImpl: PiecesRepositoryImpl): PiecesRepository

    @Binds
    abstract fun bindFavouriteMuseumsRepository(favouriteMuseumsRepositoryImpl :FavouriteMuseumsRepositoryImpl) :FavouriteMuseumsRepository

    @Binds
    abstract fun bindStoriesRepository(storiesRepositoryImpl: StoriesRepositoryImpl): StoriesRepository


    @Binds
    abstract fun bindHallsRepository(hallsRepositoryImpl: HallsRepositoryImpl): HallRepository


    @Binds
    abstract fun bindCollectionsRepository(collectionsRepositoryImpl: CollectionsRepositoryImpl): CollectionsRepository
    @Binds
    abstract fun bindFavouritePiecesRepository(favouritePieceRepositoryImpl: FavouritePieceRepositoryImpl) :FavouritePieceRepository

    @Binds
    abstract fun bindAIModelRepository(aiModelRepositoryImpl: AIModelRepositoryImpl): AIModelRepository
}