package com.lodjinha.ui.home

import com.lodjinha.data.model.Banner
import com.lodjinha.data.model.Category
import com.lodjinha.data.model.Product


class HomeContract {
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