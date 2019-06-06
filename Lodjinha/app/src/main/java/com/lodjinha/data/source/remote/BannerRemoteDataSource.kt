package com.lodjinha.data.source.remote

import com.lodjinha.BuildConfig
import com.lodjinha.data.model.DataBanner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BannerRemoteDataSource {

    private var request: ApiRequest

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.URL_BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        request = retrofit.create(ApiRequest::class.java)
    }

    fun getAllBanners(): DataBanner? {
        val call = request.requestBanners()
        return call.execute().body()
    }

}