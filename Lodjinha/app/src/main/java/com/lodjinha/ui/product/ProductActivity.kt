package com.lodjinha.ui.product

import android.app.ProgressDialog
import android.content.DialogInterface
import android.graphics.Paint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.Html
import android.view.MenuItem
import android.view.View
import com.lodjinha.R
import com.lodjinha.data.model.Product
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() , ProductContract.View{

    lateinit var presenter: ProductContract.Presenter
    val product: Product by lazy { intent.getParcelableExtra<Product>("PRODUCT") }
    private var dialogProgress: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        presenter = ProductPresenter()
        presenter.setView(this)
        configToolbar(product.categoria!!.descricao)
        configUi()
    }

    fun configUi(){
        Picasso.with(this).load(product.urlImagem).fit().centerInside().into(img_product, object : Callback {
            override fun onSuccess() {
                progress.visibility = View.GONE
                txtErrorImg.visibility = View.GONE
            }

            override fun onError() {
               progress.visibility = View.GONE
               txtErrorImg.visibility = View.VISIBLE
            }

        })
        product_name.text = product.nome
        product_description.text = Html.fromHtml(product.descricao)
        txt_precoDe.text = "${resources.getString(R.string.preco_de)} ${product.precoDe}"
        txt_precoDe.setPaintFlags(txt_precoDe.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)

        txt_precoPor.text = "${resources.getString(R.string.preco_por)} ${product.precoPor}"
    }

    fun configToolbar(title: String) {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title
    }

    fun reserveProductAction(view : View){
        showDialogLoading(resources.getString(R.string.reserve_loading))
        presenter.reserveProduct(product.id)
    }

    override fun alertDialog(message: String) {
        closeDialogLoading()
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(android.R.string.yes) { _, _ ->
                finish()
            }
            .show()
    }

    override fun showDialogLoading(message: String) {
        dialogProgress = ProgressDialog.show(this, "", message, true)
        dialogProgress?.setCancelable(false)
        dialogProgress?.isShowing
    }

    fun closeDialogLoading() {
        dialogProgress?.dismiss()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
