package com.lodjinha.ui.product

import com.lodjinha.data.model.DataProduct
import retrofit2.Call
import retrofit2.Response

interface ProductContract {

    interface View{
       fun alertDialog(message : String)
       fun showDialogLoading(message : String)
    }

    interface Model {
        fun reserveProduct(idProduct: String): Call<Any>
        fun getBestSellerProduct(): DataProduct?
    }

    interface Presenter {
        fun setView(view : View)
        fun reserveProduct(idProduct: String)
        fun handlerCall(call: Call<Any>)
        fun handlerResponse(response: Response<Any>)
    }
}