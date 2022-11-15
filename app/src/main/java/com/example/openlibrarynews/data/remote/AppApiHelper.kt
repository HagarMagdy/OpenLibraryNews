package com.example.openlibrarynews.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.openlibrarynews.data.model.NewsItem
import com.example.openlibrarynews.data.remote.services.NewsService
import com.example.openlibrarynews.data.model.reponse.BaseResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import java.util.ArrayList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppApiHelper @Inject constructor(private val newsService: NewsService) : ApiHelper {


    override fun fetchNews(queryMap: HashMap<String, String>): LiveData<List<NewsItem>> {
        val data: MutableLiveData<List<NewsItem>> = MutableLiveData<List<NewsItem>>()

          newsService.fetchNews(queryMap).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response: BaseResponse<NewsItem>? ->
                data.value = response?.articles

            }, { t: Throwable? ->
                data.value = ArrayList<NewsItem>()
            })

        return data


    }
}