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
import kotlinx.android.synthetic.main.loading_progress.view.*
import kotlinx.android.synthetic.main.product_list.view.progress


class ProductAdapter(val products: ArrayList<Product>, val hasPagination: Boolean, val onClick: (Product) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_ITEM = 1
    private val VIEW_PROG = 2
    private var closeLoad = false

    override fun getItemCount(): Int {
        return this.products.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder: RecyclerView.ViewHolder
        var view = LayoutInflater.from(parent.context).inflate(R.layout.product_list, parent, false)
        if (viewType == VIEW_ITEM) {
            holder = ProductAdapterViewHolder(view)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.loading_progress, parent, false)
            holder = ProgressViewHolder(view)
        }
        return holder
    }

    override fun getItemViewType(position: Int): Int {
        if (!hasPagination) return VIEW_ITEM

        return if (position != this.products.size - 1) VIEW_ITEM else VIEW_PROG
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductAdapterViewHolder) {
            val product = products.get(position)
            val view = holder.itemView
            with(view) {
                txt_product_name.text = product.nome
                txt_product_description.text = product.descricao
                txt_precoDe.text = "De " + product.precoDe
                txt_precoDe.setPaintFlags(txt_precoDe.getPaintFlags() or STRIKE_THRU_TEXT_FLAG)

                txt_precoPor.text = "Por " + product.precoPor

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
        } else {
            if (closeLoad) {
                holder.itemView.progress.setVisibility(View.GONE)
                holder.itemView.txt_end.setVisibility(View.VISIBLE)
            }
        }
    }

    fun closeLoad() {
        closeLoad = true
    }

    class ProductAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class ProgressViewHolder(view: View) : RecyclerView.ViewHolder(view)

}