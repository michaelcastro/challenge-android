package com.lodjinha.data.model

class DataProduct {
    var data: ArrayList<Product> = ArrayList()
    override fun toString(): String {
        return "DataProduct(products=$data)"
    }
}