package com.graduation.data.datasourceImpl

import com.graduation.data.dataSourceContract.SignUpUserDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class di {

    @Binds
    abstract fun provideSignUpDataSource(
        signUpUserDataSourceImpl: SignUpUserDataSourceImpl
    ): SignUpUserDataSource


}