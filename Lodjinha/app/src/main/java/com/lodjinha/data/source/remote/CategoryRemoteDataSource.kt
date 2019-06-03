package com.lodjinha.data.source.remote

import com.lodjinha.BuildConfig
import com.lodjinha.data.model.DataCategory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryRemoteDataSource {
    private var request: ApiRequest

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.URL_BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        request = retrofit.create(ApiRequest::class.java)
    }

    fun getAllCategories(): DataCategory? {
        val call = request.requestCategories()
        return call.execute().body()
    }
}