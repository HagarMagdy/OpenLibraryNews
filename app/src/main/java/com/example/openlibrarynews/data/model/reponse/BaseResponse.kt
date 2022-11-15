package com.example.openlibrarynews.data.model.reponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(

    @SerializedName("status") @Expose var status: String? = null,
    @SerializedName("copyright") @Expose var copyright: String? = null,
    @SerializedName("results") @Expose var articles: ArrayList<T> = arrayListOf(),
    @SerializedName("num_results") @Expose var numResults: Int? = null)

