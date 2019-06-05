package com.lodjinha.data.source.remote

import com.lodjinha.data.model.DataBanner
import com.lodjinha.data.model.DataCategory
import com.lodjinha.data.model.DataProduct
import retrofit2.Call
import retrofit2.http.*

interface ApiRequest {
    @GET("banner")
    fun requestBanners() : Call<DataBanner>

    @GET("categoria")
    fun requestCategories() : Call<DataCategory>

    @GET("produto/maisvendidos")
    fun requestBestSellerProduct() : Call<DataProduct>

    @GET("produto")
    fun requestProductByCategory(@Query("categoriaId") idCategory: String, @Query("offset") offset: Int, @Query("limit") limit: Int) : Call<DataProduct>

    @POST("produto/{produtoId}")
    fun reserveProduct(@Path("produtoId") produtoId: Int) : Call<Any>
}