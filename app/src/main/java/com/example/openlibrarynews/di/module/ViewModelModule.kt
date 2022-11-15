package com.example.openlibrarynews.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.openlibrarynews.data.remote.ApiHelper
import com.example.openlibrarynews.di.ViewModelFactory
import com.example.openlibrarynews.ui.viewmodel.NewsViewModel
import dagger.Module
import dagger.Provides


@Module
 class ViewModelModule {
    @Provides
    internal fun provideViewModel(appRepository: ApiHelper): NewsViewModel {
        return NewsViewModel(appRepository)
    }

    @Provides
    internal fun provideViewModelFactory(viewModel: NewsViewModel): ViewModelProvider.Factory {
        return ViewModelFactory(viewModel)
    }
}