package com.example.openlibrarynews.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.openlibrarynews.R
import com.example.openlibrarynews.data.model.NewsItem
import com.example.openlibrarynews.ui.adapter.NewsAdapter
import com.example.openlibrarynews.ui.viewmodel.NewsViewModel
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

const val NEWS_KEY = "news_key"

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel:NewsViewModel

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()

        viewModel.fetchNewsList()

        subscribeObservers()
        attachListeners()


        }

    private fun attachListeners() {

        // refresh swipe to refresh Layout
        swipeRefreshLayout.setOnRefreshListener {

            newsAdapter.clearList()
            false.displayProgressBar()

            subscribeObservers()
            viewModel.fetchNewsList()
            listRecycler.scrollToPosition(0)
        }
    }

    private fun subscribeObservers() {

        viewModel.result.observe(this) {
           if (it.isNotEmpty())
           {
               false.displayProgressBar()
               appendNews(it)
               listRecycler.scrollToPosition(0)
           }

        }



    }

    private fun appendNews(news: List<NewsItem>) {
        newsAdapter.addList(ArrayList(news))
    }

    private fun setupUI() {
        listRecycler.apply {
            newsAdapter = NewsAdapter(ArrayList(),NewsAdapter.OnClickListener{
            openDetailsActivity(it)
            })
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun openDetailsActivity(item: NewsItem) {
        val detailsIntent = Intent(this, DetailsActivity::class.java).apply {
            putExtra(NEWS_KEY, item)
        }
        startActivity(detailsIntent)

    }

    private fun Boolean.displayProgressBar() {
        newsAdapter.updateHideFooter(!this)
        if (this)
            listRecycler.scrollToPosition(newsAdapter.itemCount - 1)
        else
            swipeRefreshLayout.isRefreshing = false
    }
    }


