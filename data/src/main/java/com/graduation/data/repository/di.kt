package com.graduation.data.repository

import com.graduation.domain.repositories.MuseumRepository
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
}