package com.lodjinha.data.source.remote

import com.lodjinha.data.model.Banner
import com.lodjinha.data.model.DataBanner
import com.lodjinha.data.model.DataCategory
import com.lodjinha.data.model.DataProduct
import retrofit2.Call
import retrofit2.http.GET
import java.util.ArrayList

interface ApiRequest {
    @GET("banner")
    fun requestBanners() : Call<DataBanner>

    @GET("categoria")
    fun requestCategories() : Call<DataCategory>

    @GET("produto/maisvendidos")
    fun requestBestSellerProduct() : Call<DataProduct>
}