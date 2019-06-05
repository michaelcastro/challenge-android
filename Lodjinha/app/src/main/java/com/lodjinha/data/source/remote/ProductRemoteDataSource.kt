package com.lodjinha.data.source.remote

import com.lodjinha.BuildConfig
import com.lodjinha.data.model.DataProduct
import com.lodjinha.ui.product.ProductContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductRemoteDataSource : ProductContract.Model {
    private var request: ApiRequest

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.URL_BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        request = retrofit.create(ApiRequest::class.java)
    }

    override fun getBestSellerProduct(): DataProduct? {
        val call = request.requestBestSellerProduct()
        return call.execute().body()
    }

    override fun reserveProduct(idProduct : String): Call<Any> {
        val call = request.reserveProduct(idProduct.toInt())
        return call
    }
}