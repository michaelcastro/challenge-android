package com.lodjinha.ui.category

import com.lodjinha.data.model.DataCategory
import com.lodjinha.data.model.Product

interface CategoryContract {
    interface View {
        fun loadProducts(idCategory: String, offset : Int, limit : Int)
        fun updateProducts(data: ArrayList<Product>)
        fun addListProducts(data: ArrayList<Product>)
        fun setView(view : View)
        fun progressLoading(show: Boolean)
        fun showToast(message: String)
        fun openProductActivity(product: Product)
    }

    interface Presenter {
        var offset : Int
        var limit : Int
        var page : Int
        fun loadProducts(idCategory: String, offset : Int, limit : Int)
        fun loadProductsNextPage(id: String, totalItemCount : Int, lastVisibleItem : Int)
        fun updateProducts(data: ArrayList<Product>)
        fun setView(view : View)
        fun showToast(message: String)
        fun progressLoading(show: Boolean)
        fun openProductActivity(product: Product)
    }

    interface Model {
        fun loadProducts(idCategory: String, offset : Int, limit : Int) : ArrayList<Product>
        fun getAllCategories(): DataCategory?
    }
}