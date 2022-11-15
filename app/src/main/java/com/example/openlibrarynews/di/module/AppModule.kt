package com.example.openlibrarynews.di.module

import com.example.openlibrarynews.NewsApp
import com.example.openlibrarynews.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    @ApplicationContext
    internal fun provideContext(application: NewsApp) = application



}