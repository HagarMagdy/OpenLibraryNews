package com.example.openlibrarynews

import com.example.openlibrarynews.di.component.AppComponent
import com.example.openlibrarynews.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class NewsApp: DaggerApplication() {


    companion object {
        private lateinit var instance: NewsApp
    }

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

        instance = this
    }


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component: AppComponent =
            DaggerAppComponent.builder().application(this).build()
        component.inject(this)
        return component
    }

}

