package com.example.openlibrarynews.data.remote.services

import com.example.openlibrarynews.data.model.NewsItem
import com.example.openlibrarynews.data.model.reponse.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NewsService {
    /**
     * This interface represents the endpoint apis of news list
     */

    @GET("all-sections/30.json")
    fun fetchNews(@QueryMap queryMap: HashMap<String, String>): Observable<BaseResponse<NewsItem>>

}