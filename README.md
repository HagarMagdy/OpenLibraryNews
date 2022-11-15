# OpenLibraryArticles

This repository contains a sample project in Kotlin,MVVM, RxJava, Dagger and Retrofit.


![image](https://user-images.githubusercontent.com/23075249/202003633-fa9c0613-045e-4545-8814-e57657f0b2b1.png)


### The app has the following base packages:

* di: Dagger classes to work with Network.

* data: data classes which are used by UI and also remote data.

* data/remote: Services and network models.

* utills: Utility class.

* ui: View classes along with their corresponding ViewModel.

# Run tests:

### (test) packages

* class OpenLibraryServiceTest in (data.remote package) contains testing the retrofit service.
* class MainViewModelIntegrationTest in (ui package) contains testing viewmodel if there's news.


# Library reference resources:

* Dagger: https://developer.android.com/training/dependency-injection/dagger-android

* MVVM Architecture : https://developer.android.com/jetpack/guide

* RxJava: https://developers.google.com/maps/documentation/android-sdk/rx

* Glide: https://github.com/bumptech/glide

* Retrofit: https://square.github.io/retrofit/

