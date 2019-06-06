package com.lodjinha.ui.home

import com.lodjinha.data.model.*

class HomeContract {

    interface Model {
        fun getAllBanners(): DataBanner?
        fun getBestSellerProduct(): DataProduct?
        fun getAllCategories(): DataCategory?
    }

    interface View {
        fun loadBanners()
        fun loadCategories()
        fun loadProducts()
        fun updateBanners(data: ArrayList<Banner>)
        fun updateCategories(data: ArrayList<Category>)
        fun updateProducts(data: ArrayList<Product>)
        fun showToast(message: String)
        fun openProductActivity(product: Product)
        fun openCategoryDetailActivity(category: Category)
    }

    interface Presenter {
        fun loadBanners()
        fun loadCategories()
        fun loadProducts()
        fun updateBanners(data: ArrayList<Banner>)
        fun updateCategories(data: ArrayList<Category>)
        fun updateProducts(data: ArrayList<Product>)
        fun setView(view : View)
        fun showToast(message: String)
        fun openProductActivity(product: Product)
        fun openCategoryDetailActivity(category: Category)
    }
}