package com.lodjinha.ui.category

import android.annotation.SuppressLint
import android.util.Log
import com.lodjinha.data.model.Product
import com.lodjinha.data.source.remote.CategoryRemoteDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryPresenter : CategoryContract.Presenter {
    override var offset = 0
    override var limit = 20
    override var page = 20

    internal lateinit var view: CategoryContract.View
    private var model: CategoryContract.Model = CategoryRemoteDataSource()

    @SuppressLint("CheckResult")
    override fun loadProductsNextPage(id: String, totalItemCount: Int, lastVisibleItem: Int) {
        if ((totalItemCount - 1) == lastVisibleItem) {
            offset += page
            limit += page
            Observable.fromCallable {
                model.loadProducts(id, offset, limit)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.addListProducts(it)
                },
                    { view.showToast(it.message!!) }
                )
        }
    }

    @SuppressLint("CheckResult")
    override fun loadProducts(idCategory: String, offset: Int, limit: Int) {
        view.progressLoading(true)

        Observable.fromCallable {
            model.loadProducts(idCategory, offset, limit)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                updateProducts(it)
            },
                { view.showToast(it.message!!) }
            )
    }

    override fun updateProducts(data: ArrayList<Product>) {
        view.progressLoading(false)
        view.updateProducts(data)
    }

    override fun setView(view: CategoryContract.View) {
        this.view = view
    }

    override fun showToast(message: String) {
        view.showToast(message)
    }

    override fun openProductActivity(product: Product) {
        view.openProductActivity(product)
    }

    override fun progressLoading(show: Boolean) {
        view.progressLoading(show)
    }

}