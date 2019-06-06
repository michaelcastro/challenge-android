package com.lodjinha

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.lodjinha.data.model.DataCategory
import com.lodjinha.data.model.Product
import com.lodjinha.data.source.remote.CategoryRemoteDataSource
import com.lodjinha.data.source.remote.ProductRemoteDataSource
import com.lodjinha.ui.category.CategoryActivity
import kotlinx.android.synthetic.main.activity_category.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryActivityTest {

    var dataCategory: DataCategory? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val model = CategoryRemoteDataSource()
        dataCategory =  model.getAllCategories()
    }

    @Test
    fun listProductsByCategory() {
        val mActivityRule = ActivityTestRule(CategoryActivity::class.java)
        val i = Intent()
        i.putExtra("CATEGORY", dataCategory!!.data.get(0))
        mActivityRule.launchActivity(i)
        Thread.sleep(1000)
        onView(ViewMatchers.withId(R.id.recycler_categories)).perform(actionOnItemAtPosition< RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(1000)
    }

}