package com.lodjinha.ui.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lodjinha.R
import com.lodjinha.data.model.Category
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cards_grid.view.*
import kotlin.collections.ArrayList

class CardGroupAdapter(val categories: ArrayList<Category>, val onClick: (Category) -> Unit) :
    RecyclerView.Adapter<CardGroupAdapter.CardGroupAdapterViewHolder>() {

    override fun getItemCount(): Int {
        return this.categories.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardGroupAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cards_grid, parent, false)
        val holder = CardGroupAdapterViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: CardGroupAdapterViewHolder, position: Int) {
        val category = categories.get(position)
        val view = holder.itemView
        with(view) {
            txt_category_name.text = category.descricao

            Picasso.with(context).load(category.urlImagem).fit().centerInside().into(img_category, object : Callback {
                override fun onSuccess() {
                    progress.visibility = View.GONE
                    txtErrorImg.visibility = View.GONE
                }

                override fun onError() {
                    progress.visibility = View.GONE
                    txtErrorImg.visibility = View.VISIBLE
                }

            })
            setOnClickListener { onClick(category) }
        }
    }

    class CardGroupAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view)
}