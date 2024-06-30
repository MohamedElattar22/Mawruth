//package com.graduation.data.api.modelapi
//
//import android.content.Context
//import android.content.SharedPreferences
//import android.util.Log
//import com.graduation.data.api.ApiConstants
//import com.graduation.data.api.TokenInterceptor
//import com.graduation.data.api.WebServices
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import okhttp3.Interceptor
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//@Module
//@InstallIn(SingletonComponent::class)
//class ModelApiModule {
//    @Provides
//    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
//        val logging = HttpLoggingInterceptor {
//            Log.e("ModelApi", it)
//        }
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//        return logging
//    }
//
//    @Provides
//    fun providesGsonConverterFactory(): GsonConverterFactory {
//        return GsonConverterFactory.create()
//    }
//
//    @Provides
//    fun provideHttpClient(
//        loggingInterceptor: HttpLoggingInterceptor,
//        tokenInterceptor: Interceptor
//    )
//            : OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .addInterceptor(tokenInterceptor)
//            .build()
//    }
//
//    @Provides
//    fun provideRetrofit(
//        converterFactory: GsonConverterFactory,
//        httpClient: OkHttpClient
//    ): Retrofit {
//        return Retrofit
//            .Builder()
//            .addConverterFactory(converterFactory)
//            .client(httpClient)
//            .baseUrl(ModelApiConstants.baseUrl)
//            .build()
//    }
//
//    @Provides
//    fun provideWebServices(retrofit: Retrofit): ModelWebService {
//        return retrofit.create(ModelWebService::class.java)
//    }
//
//
//
//}