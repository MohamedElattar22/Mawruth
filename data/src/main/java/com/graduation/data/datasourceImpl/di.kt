package com.graduation.data.datasourceImpl

import com.graduation.data.dataSourceContract.UserAuthenticationDataSource
import com.graduation.data.dataSourceContract.musums.MuseumsDataSource
import com.graduation.data.datasourceImpl.museums.MuseumsDataSourceImpl
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

}