package com.example.openlibrarynews.di.component

import android.app.Application
import com.example.openlibrarynews.NewsApp
import com.example.openlibrarynews.di.module.ActivityBindingModule
import com.example.openlibrarynews.di.module.AppModule
import com.example.openlibrarynews.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component (
    modules = [AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        AppModule::class,NetworkModule::class, ActivityBindingModule::class]
        )
interface AppComponent : AndroidInjector<DaggerApplication>{


      fun inject(application: NewsApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application?): Builder
        fun build(): AppComponent
    }

}