package com.lodjinha.ui.home

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.lodjinha.R
import com.lodjinha.data.model.Banner
import com.lodjinha.data.model.Category
import kotlinx.android.synthetic.main.fragment_home.*
import android.support.v7.widget.LinearLayoutManager
import com.lodjinha.data.model.Product
import com.lodjinha.ui.category.CategoryActivity
import com.lodjinha.ui.product.ProductActivity


class HomeFragment : Fragment(), HomeContract.View {

    lateinit var presenter: HomeContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = HomePresenter()
        presenter.setView(this)
        loadBanners()
        loadCategories()
        loadProducts()
    }

    override fun loadCategories() {
        presenter.loadCategories()
    }

    override fun loadBanners() {
        presenter.loadBanners()
    }

    override fun loadProducts() {
        presenter.loadProducts()
    }

    override fun updateProducts(data: ArrayList<Product>) {
        val adapter = ProductAdapter(data, false) {
            presenter.openProductActivity(it)
        }
        recycler_best_seller.setHasFixedSize(true)
        recycler_best_seller.setAdapter(adapter)
        recycler_best_seller.addItemDecoration(
            DividerItemDecoration(
                recycler_best_seller.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycler_best_seller.setLayoutManager(mLayoutManager)
    }


    override fun openProductActivity(product: Product) {
        val intent = Intent(context, ProductActivity::class.java)
        intent.putExtra("PRODUCT", product)
        startActivity(intent)
    }

    override fun updateBanners(data: ArrayList<Banner>) {
        view_pager_banner.adapter = SliderBannerAdapter(context!!, data)
        indicator.setupWithViewPager(view_pager_banner, true)
    }

    override fun updateCategories(data: ArrayList<Category>) {
        val adapter = CardGroupAdapter(data) {
            presenter.openCategoryDetailActivity(it)
        }
        recycler_view_categories.setHasFixedSize(true)
        recycler_view_categories.setAdapter(adapter)
        val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycler_view_categories.setLayoutManager(mLayoutManager)
    }

    override fun openCategoryDetailActivity(category: Category) {
        val intent = Intent(context, CategoryActivity::class.java)
        intent.putExtra("CATEGORY", category)
        startActivity(intent)
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    companion object {

        internal val TAG = "HomeFragment"

        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
