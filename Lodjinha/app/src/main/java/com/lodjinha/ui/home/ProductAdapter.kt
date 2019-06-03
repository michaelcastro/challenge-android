package com.lodjinha.ui.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lodjinha.R
import com.lodjinha.data.model.Product
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_list.view.*
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.graphics.Paint


class ProductAdapter(val products: ArrayList<Product>, val onClick: (Product) -> Unit) :
    RecyclerView.Adapter<ProductAdapter.ProductAdapterViewHolder>() {

    override fun getItemCount(): Int {
        return this.products.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list, parent, false)
        val holder = ProductAdapterViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ProductAdapterViewHolder, position: Int) {
        val product = products.get(position)
        val view = holder.itemView
        with(view) {
            txt_product_name.text = product.nome
            txt_product_description.text = product.descricao
            txt_precoDe.text = "De "+product.precoDe
            txt_precoDe.setPaintFlags(txt_precoDe.getPaintFlags() or STRIKE_THRU_TEXT_FLAG)

            txt_precoPor.text = "Por "+product.precoPor

            Picasso.with(context).load(product.urlImagem).fit().centerInside().into(img_product, object : Callback {
                override fun onSuccess() {
                    progress.visibility = View.GONE
                    txtErrorImg.visibility = View.GONE
                }

                override fun onError() {
                    progress.visibility = View.GONE
                    txtErrorImg.visibility = View.VISIBLE
                }

            })
            setOnClickListener { onClick(product) }
        }
    }

    class ProductAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view)
}