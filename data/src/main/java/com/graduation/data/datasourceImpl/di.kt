package com.graduation.data.datasourceImpl

import com.graduation.data.dataSourceContract.EditUserInfoDataSource
import com.graduation.data.dataSourceContract.FavouriteMuseumsDataSource
import com.graduation.data.dataSourceContract.FavouritePiecesDataSource
import com.graduation.data.dataSourceContract.ReviewsDataSource
import com.graduation.data.dataSourceContract.UserAuthenticationDataSource
import com.graduation.data.dataSourceContract.UserInformationDataSource
import com.graduation.data.dataSourceContract.categories.CategoriesDataSource
import com.graduation.data.dataSourceContract.collections.CollectionsDataSource
import com.graduation.data.dataSourceContract.halls.HallsDataSource
import com.graduation.data.dataSourceContract.musums.MuseumsDataSource
import com.graduation.data.dataSourceContract.pieces.PiecesDataStore
import com.graduation.data.dataSourceContract.stories.StoriesDataSource
import com.graduation.data.datasourceImpl.categories.CategoriesDataSourceImpl
import com.graduation.data.datasourceImpl.collections.CollectionsDataSourceImpl
import com.graduation.data.datasourceImpl.halls.HallsDataSourceImpl
import com.graduation.data.datasourceImpl.museums.MuseumsDataSourceImpl
import com.graduation.data.datasourceImpl.stories.StoriesDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class di {

    @Binds
    abstract fun provideSignUpDataSource(
        userAuthenticationDataSourceImpl: UserAuthenticationDataSourceImpl
    ): UserAuthenticationDataSource

    @Binds
    abstract fun bindMuseumDataSource(museumsDataSourceImpl: MuseumsDataSourceImpl): MuseumsDataSource

    @Binds
    abstract fun bindUserInfoDataSource(userInformationDataSourceImpl: UserInformationDataSourceImpl): UserInformationDataSource

    @Binds
    abstract fun bindEditUserInfoDataSource(editUserInfoDataSourceImpl: EditUserInfoDataSourceImpl): EditUserInfoDataSource

    @Binds
    abstract fun bindCategories(categoryDataSourceImpl: CategoriesDataSourceImpl)
            : CategoriesDataSource

    @Binds
    abstract fun bindReviewsDataSource(reviewsDataSourceImpl: ReviewsDataSourceImpl)
            : ReviewsDataSource

    @Binds
    abstract fun bindPiecesDataSource(piecesDataStoreImpl: PiecesDataStoreImpl)
            : PiecesDataStore

    @Binds
    abstract fun bindFavouriteMuseumsDataSource(favouriteMuseumsDataSourceImpl: FavouriteMuseumsDataSourceImpl)
            : FavouriteMuseumsDataSource

    @Binds
    abstract fun bindStoriesDataSource(storiesDataSourceImpl: StoriesDataSourceImpl): StoriesDataSource


    @Binds
    abstract fun bindHallsDataSource(hallsDataSourceImpl: HallsDataSourceImpl): HallsDataSource

    @Binds
    abstract fun bindCollectionsDataSource(collectionsDataSourceImpl: CollectionsDataSourceImpl): CollectionsDataSource

    @Binds
    abstract fun bindFavouritePiecesDataSource(favouritePiecesDataSourceImpl: FavouritePiecesDataSourceImpl)
    : FavouritePiecesDataSource

}