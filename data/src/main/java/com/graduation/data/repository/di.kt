package com.graduation.data.repository

import com.graduation.domain.repositories.SignUpRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class di {

    @Binds
    abstract fun bindSignUpRepo(
        repository: SignUpRepositoryImpl
    ): SignUpRepository

}