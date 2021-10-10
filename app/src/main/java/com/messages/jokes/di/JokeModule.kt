package com.messages.jokes.di

import com.messages.jokes.network.JokeServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class JokeModule {

    @Provides
    @Singleton
    fun provideJokeApiService(retrofit: Retrofit) = retrofit.create(JokeServices::class.java)
}