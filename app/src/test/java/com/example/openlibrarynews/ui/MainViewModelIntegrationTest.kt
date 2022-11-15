package com.example.openlibraryarticles.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer

import com.example.openlibrarynews.data.model.NewsItem
import com.example.openlibrarynews.data.remote.ApiHelper
import com.example.openlibrarynews.data.remote.AppApiHelper
import com.example.openlibrarynews.data.remote.services.NewsService
import com.example.openlibrarynews.data.model.reponse.BaseResponse
import com.example.openlibrarynews.ui.viewmodel.NewsViewModel
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class MainViewModelIntegrationTest {

    @get:Rule
    val mockWebServer = MockWebServer()

    @get:Rule
    var testRule: TestRule = InstantTaskExecutorRule()

    @MockK
    lateinit var stateObserver: Observer<List<NewsItem>>

    private lateinit var viewModel: NewsViewModel

    lateinit var repository: ApiHelper

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
        repository = AppApiHelper(openLibraryService)
        viewModel = NewsViewModel(repository)
        viewModel.result.observeForever(stateObserver)
    }

    @Test
    fun `get news change view state to Success if there is news`() = runBlocking {
        val result = repository.fetchNews(hashMapOf())
        var finalDataState: List<NewsItem>? = null
        finalDataState = result.value
        delay(100)
        val response = Gson().fromJson(dummyResponseJson, BaseResponse::class.java)
        assertThat(finalDataState).isEqualTo(
            response.articles
        )
    }

}