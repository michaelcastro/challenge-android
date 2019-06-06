package com.lodjinha

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.lodjinha.data.model.DataProduct
import com.lodjinha.data.source.remote.ProductRemoteDataSource
import com.lodjinha.ui.product.ProductActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import android.content.Intent
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ProductActivityTest {

    var dataProduct: DataProduct? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val model = ProductRemoteDataSource()
        dataProduct =  model.getBestSellerProduct()
    }

    @Test
    fun reserveProduct() {
        val mActivityRule = ActivityTestRule(ProductActivity::class.java)
        val i = Intent()
        i.putExtra("PRODUCT", dataProduct!!.data.get(0))
        mActivityRule.launchActivity(i)
        Thread.sleep(1000)
        onView(withId(R.id.fab_reservar_produto)).perform(click())
        Thread.sleep(1000)
    }

}