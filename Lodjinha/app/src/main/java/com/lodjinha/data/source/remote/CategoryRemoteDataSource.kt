package com.lodjinha.data.source.remote

import android.annotation.SuppressLint
import com.lodjinha.BuildConfig
import com.lodjinha.data.model.DataCategory
import com.lodjinha.data.model.Product
import com.lodjinha.ui.category.CategoryContract
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryRemoteDataSource : CategoryContract.Model {

    private var request: ApiRequest

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.URL_BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        request = retrofit.create(ApiRequest::class.java)
    }

    override fun getAllCategories(): DataCategory? {
        val call = request.requestCategories()
        return call.execute().body()
    }

    @SuppressLint("CheckResult")
    override fun loadProducts(idCategory: String, offset: Int, limit: Int): ArrayList<Product> {
        val call = request.requestProductByCategory(idCategory, offset, limit)
        return call.execute().body()!!.data
    }
}