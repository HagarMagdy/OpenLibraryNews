package com.example.openlibrarynews.ui.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import coil.ImageLoader
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.ImageRequest
import com.example.openlibrarynews.R
import kotlinx.android.synthetic.main.image_viewer_dialog.*

class ImageViewerDialog(private val url: String) : DialogFragment() {

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireActivity(), dagger.android.support.R.style.Theme_AppCompat_Light_NoActionBar)

        val view = requireActivity().layoutInflater.inflate(R.layout.image_viewer_dialog, null)

        val d = ColorDrawable(Color.BLACK)
        d.alpha = 140

        dialog.window!!.setBackgroundDrawable(d)
        dialog.window!!.setContentView(view)

        val params = dialog.window!!.attributes
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        params.gravity = Gravity.CENTER

        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)

        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.image_viewer_dialog, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        close.setOnClickListener {
            this.isCancelable = true
            dismiss()
        }

        initView()
    }

    private fun initView() {

        val imageLoader = context?.let {
            ImageLoader.Builder(it)
                .memoryCache {
                    MemoryCache.Builder(it)
                        .maxSizePercent(0.25)
                        .build()
                }
                .diskCache {
                    DiskCache.Builder()
                        .directory(it.cacheDir.resolve("image_cache"))
                        .maxSizePercent(0.02)
                        .build()
                }
                .build()

        }

        val request = context?.let {
            ImageRequest.Builder(it)
                .crossfade(true)
                .crossfade(500)
                .placeholder(R.drawable.ic_launcher_background)
                .data(url)
                .target(image)
                .build()
        }

        if (request != null) {
            imageLoader?.enqueue(request)
        }


    }

}