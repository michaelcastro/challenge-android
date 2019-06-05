package com.lodjinha.ui.product

import android.annotation.SuppressLint
import android.util.Log
import com.lodjinha.data.source.remote.ProductRemoteDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductPresenter : ProductContract.Presenter {

    internal lateinit var view: ProductContract.View

    override fun setView(view: ProductContract.View) {
        this.view = view
    }

    private var model: ProductContract.Model = ProductRemoteDataSource()

    @SuppressLint("CheckResult")
    override fun reserveProduct(idProduct: String) {

        Observable.fromCallable {
            model.reserveProduct(idProduct)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                handlerCall(it)
            },
                { view.alertDialog(it.message!!) }
            )
    }

    override fun handlerCall(call: Call<Any>) {

        call.enqueue(object : Callback<Any> {
            override fun onFailure(call: Call<Any>, t: Throwable) {
                view.alertDialog(call.execute().message())
            }

            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                handlerResponse(response)
            }
        })
    }

    override fun handlerResponse(response: Response<Any>) {
        when (response.code()) {
            200 -> {
                view.alertDialog("Produto reservado com sucesso")
            }
            201 -> {
                view.alertDialog("Produto criado com sucesso")
            }
            401 -> {
                view.alertDialog("Unauthorized")
            }
            403 -> {
                view.alertDialog("Forbidden")
            }
            404 -> {
                view.alertDialog("Not Found")
            }
            else -> {
                view.alertDialog("Desconhecido "+response.code())
            }
        }
    }
}