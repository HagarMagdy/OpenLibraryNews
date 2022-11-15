package com.example.openlibrarynews.data.remote

import com.example.openlibrarynews.data.model.NewsItem
import com.example.openlibrarynews.data.remote.services.NewsService
import com.example.openlibrarynews.data.model.reponse.BaseResponse
import com.example.openlibrarynews.utils.Config
import io.mockk.MockKAnnotations
import io.reactivex.schedulers.Schedulers
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class OpenLibraryServiceTest {
    @get:Rule
    val mockWebServer = MockWebServer()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val dummyResponseJson = ClassLoader.getSystemResource("articles.json").readText()

    private val openLibraryService by lazy {
        retrofit.create(NewsService::class.java)
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        mockWebServer.enqueue(
            MockResponse()
                .setBody(dummyResponseJson)
                .setResponseCode(200)
        )
    }

    @Test
    fun `get articles load all the news from the json file as expected`() {


        val map = hashMapOf(Config.apiKey to Config.apiKeyValue)
        val response =   openLibraryService.fetchNews(map)

        response.subscribeOn(Schedulers.io())
            .subscribe({ response: BaseResponse<NewsItem>? ->
                Assert.assertEquals(response?.articles?.size, 2)

            }, { t: Throwable? ->

            })
    }


}