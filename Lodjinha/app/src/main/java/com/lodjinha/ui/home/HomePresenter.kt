package com.lodjinha.ui.home

import android.annotation.SuppressLint
import android.util.Log
import com.lodjinha.data.model.Banner
import com.lodjinha.data.model.Category
import com.lodjinha.data.model.Product
import com.lodjinha.data.source.remote.BannerRemoteDataSource
import com.lodjinha.data.source.remote.CategoryRemoteDataSource
import com.lodjinha.data.source.remote.ProductRemoteDataSource
import com.lodjinha.ui.category.CategoryContract
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenter : HomeContract.Presenter {

    override fun openProductActivity(product: Product) {
        view.openProductActivity(product)
    }

    override fun openCategoryDetailActivity(category: Category) {
        view.openCategoryDetailActivity(category)
    }

    @SuppressLint("CheckResult")
    override fun loadProducts() {
        val productRemoteDataSource = ProductRemoteDataSource()
        Observable.fromCallable {
            productRemoteDataSource.getBestSellerProduct()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                updateProducts(it!!.data)
            },
                { showToast(it.message!!) }
            )
    }

    override fun updateProducts(data: ArrayList<Product>) {
        view.updateProducts(data)
    }

    override fun updateCategories(data: ArrayList<Category>) {
        view.updateCategories(data)
    }

    @SuppressLint("CheckResult")
    override fun loadCategories() {
        val categoryRemoteDataSource = CategoryRemoteDataSource()
        Observable.fromCallable {
            categoryRemoteDataSource.getAllCategories()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                updateCategories(it!!.data)
            },
                { showToast(it.message!!) }
            )
    }

    internal lateinit var view: HomeContract.View

    override fun updateBanners(data: ArrayList<Banner>) {
        view.updateBanners(data)
    }

    override fun setView(view: HomeContract.View) {
        this.view = view
    }

    override fun showToast(message: String) {
        view.showToast(message)
    }

    @SuppressLint("CheckResult")
    override fun loadBanners() {
        val bannerRemoteDataSource = BannerRemoteDataSource()
        Observable.fromCallable {
            bannerRemoteDataSource.getAllBanners()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                updateBanners(it!!.data)
            },
                { showToast(it.message!!) }
            )
    }
}