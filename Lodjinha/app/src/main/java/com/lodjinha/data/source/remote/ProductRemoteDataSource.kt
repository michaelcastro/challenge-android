package com.lodjinha.data.source.remote

import com.lodjinha.BuildConfig
import com.lodjinha.data.model.DataProduct
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductRemoteDataSource {
    private var request: ApiRequest

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.URL_BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        request = retrofit.create(ApiRequest::class.java)
    }

    fun getBestSellerProduct(): DataProduct? {
        val call = request.requestBestSellerProduct()
        return call.execute().body()
    }
}