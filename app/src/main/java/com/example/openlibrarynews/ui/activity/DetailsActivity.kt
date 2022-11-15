package com.example.openlibrarynews.ui.activity

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.openlibrarynews.R
import com.example.openlibrarynews.data.model.NewsItem
import com.example.openlibrarynews.ui.dialog.ImageViewerDialog
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        onNewIntent(intent)

        backImg.setOnClickListener {
            finish()
        }

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        intent?.let {
            if (it.hasExtra(NEWS_KEY)) {
                val newsObj = it.extras?.let { extras -> extras[NEWS_KEY] as NewsItem }
                newsObj?.let { item -> fillUI(item) }
            }
        }

    }

    private fun fillUI(item: NewsItem) {

        art_title.text=item.title
        publish_date.text= item.publishedDate


        source.text=item.source
        description.text=item.abstract

        poster.setOnClickListener {
            if (item.media.size>0)
            item.media[0].mediaMetadata[1].url?.let { it1 ->
                ImageViewerDialog(
                    it1
                ).show(supportFragmentManager, "ImageViewerDialog")
            }
            else
                Toast.makeText(this,getString(R.string.error_loading_image),Toast.LENGTH_SHORT).show()
        }

        if (item.media.size>0)
            Glide.with(this).load(item.media[0].mediaMetadata[1].url)
                .addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        // hide the isbn image if there is no image to preview
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        // show the isbn image when image loaded successfully
                        poster.visibility = View.VISIBLE
                        return false
                    }

                })
                .into(poster)

    }

}