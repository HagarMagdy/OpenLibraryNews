package com.example.openlibrarynews.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.openlibrarynews.R
import com.example.openlibrarynews.data.model.NewsItem
import com.example.openlibrarynews.ui.adapter.base.BaseAdapter
import com.example.openlibrarynews.ui.adapter.base.TYPE_FOOTER
import com.example.openlibrarynews.ui.adapter.base.TYPE_ITEM
import com.example.openlibrarynews.ui.adapter.viewholders.LoaderViewHolder
import com.example.openlibrarynews.ui.adapter.viewholders.NewsViewHolder

class NewsAdapter( private val news: ArrayList<NewsItem>,
                 private  val onClickListener: OnClickListener
) : BaseAdapter<NewsItem>()  {

    init {
        arrayList = news
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_FOOTER) {
            val view = layoutInflater.inflate(R.layout.cell_load_item, parent, false)
            LoaderViewHolder(view)
        } else {
            val view = layoutInflater.inflate(R.layout.cell_article, parent, false)
            NewsViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) != TYPE_FOOTER)
            (holder as NewsViewHolder).bind(news[position],onClickListener)
        else
            (holder as LoaderViewHolder).bind(hideFooter)
    }

    /**
     * Function to get the cell type using position
     * @return it returns @TYPE_ITEM or @TYPE_FOOTER if it's loading
     */
    override fun getItemViewType(position: Int): Int {
        return if (arrayList.size - 1 >= position) TYPE_ITEM else TYPE_FOOTER
    }
    class OnClickListener(val clickListener: (item: NewsItem) -> Unit) {
        fun onClick(item: NewsItem) = clickListener(item)

}

}