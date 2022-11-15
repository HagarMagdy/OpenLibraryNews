package com.example.openlibrarynews.di.module

import com.example.openlibrarynews.ui.activity.DetailsActivity
import com.example.openlibrarynews.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
     abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun bindDetailsActivity(): DetailsActivity
}