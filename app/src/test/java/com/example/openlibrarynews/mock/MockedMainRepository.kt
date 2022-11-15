package com.example.openlibrarynews.mock


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.openlibrarynews.data.model.NewsItem
import com.example.openlibrarynews.data.remote.ApiHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class MockedMainRepository : ApiHelper {

    val dummyResponse = listOf(
        NewsItem(title = "Article1"),
        NewsItem(title = "Article2"),
        NewsItem(title = "Article3"),
        NewsItem(title = "Article4")
    )

    private val mockedSuccessFlow = dummyResponse

    private val mockedEmptyListFlow = listOf<NewsItem>()

    private val mockedErrorFlow = listOf<NewsItem>()

    lateinit var currentMockedResponse: MockResponseStatus

    override fun fetchNews(queryMap: HashMap<String, String>): LiveData<List<NewsItem>> {
        val data: MutableLiveData<List<NewsItem>> = MutableLiveData<List<NewsItem>>()

        when (currentMockedResponse) {
            is MockResponseStatus.Success -> data.value = mockedSuccessFlow
            is MockResponseStatus.EMPTY -> data.value = mockedEmptyListFlow
            is MockResponseStatus.Error -> data.value = mockedErrorFlow
        }
        return data
    }
}

sealed class MockResponseStatus {
    object Success : MockResponseStatus()
    object Error : MockResponseStatus()
    object EMPTY : MockResponseStatus()
}