package com.messages.jokes.di

import android.content.Context
import com.messages.jokes.utils.DataPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferenceModule {

    @Provides
    @Singleton
    fun providePreferenceService(context: Context): DataPreference = DataPreference(context)
}