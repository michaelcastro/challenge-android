package com.lodjinha.ui.category

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.lodjinha.R
import com.lodjinha.data.model.Category
import com.lodjinha.data.model.Product
import com.lodjinha.ui.home.ProductAdapter
import com.lodjinha.ui.product.ProductActivity
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity(), CategoryContract.View {


    lateinit var presenter: CategoryContract.Presenter
    lateinit var products: ArrayList<Product>
    val category: Category by lazy { intent.getParcelableExtra<Category>("CATEGORY") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        presenter = CategoryPresenter()
        setView(this)
        loadProducts(category.id, presenter.offset, presenter.limit)
        configToolbar(category.descricao)
    }

    fun configToolbar(title: String) {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title
    }

    override fun addListProducts(data: ArrayList<Product>) {
        if (data.isEmpty()) {
            (recycler_categories.adapter as ProductAdapter).closeLoad()
        }
        data.add(Product())
        products.removeAt(products.lastIndex)
        products.addAll(data)
        recycler_categories.adapter!!.notifyDataSetChanged()
    }

    override fun updateProducts(data: ArrayList<Product>) {
        data.add(Product())
        products = data
        val adapter = ProductAdapter(products, true) {
            presenter.openProductActivity(it)
        }
        recycler_categories.setHasFixedSize(true)
        recycler_categories.setAdapter(adapter)
        recycler_categories.addItemDecoration(
            DividerItemDecoration(
                recycler_categories.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        val mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_categories.setLayoutManager(mLayoutManager)

        recycler_categories.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                presenter.loadProductsNextPage(
                    category.id,
                    mLayoutManager.itemCount,
                    mLayoutManager.findLastVisibleItemPosition()
                )
            }
        })
    }

    override fun loadProducts(idCategory: String, offset: Int, limit: Int) {
        presenter.loadProducts(idCategory, offset, limit)
    }

    override fun progressLoading(show: Boolean) {
        ll_loading.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun setView(view: CategoryContract.View) {
        presenter.setView(view)
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun openProductActivity(product: Product) {
        val intent = Intent(this, ProductActivity::class.java)
        intent.putExtra("PRODUCT", product)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
