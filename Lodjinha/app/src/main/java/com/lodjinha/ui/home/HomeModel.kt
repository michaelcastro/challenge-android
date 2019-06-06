package com.lodjinha.ui.home

import com.lodjinha.data.model.DataBanner
import com.lodjinha.data.model.DataCategory
import com.lodjinha.data.model.DataProduct
import com.lodjinha.data.source.remote.BannerRemoteDataSource
import com.lodjinha.data.source.remote.CategoryRemoteDataSource
import com.lodjinha.data.source.remote.ProductRemoteDataSource

class HomeModel : HomeContract.Model {

    override fun getAllBanners(): DataBanner? {
        val bannerRemoteDataSource = BannerRemoteDataSource()
        return bannerRemoteDataSource.getAllBanners()
    }

    override fun getBestSellerProduct(): DataProduct? {
        val productRemoteDataSource = ProductRemoteDataSource()
        return productRemoteDataSource.getBestSellerProduct()
    }

    override fun getAllCategories(): DataCategory? {
        val categoryRemoteDataSource = CategoryRemoteDataSource()
        return categoryRemoteDataSource.getAllCategories()
    }
}