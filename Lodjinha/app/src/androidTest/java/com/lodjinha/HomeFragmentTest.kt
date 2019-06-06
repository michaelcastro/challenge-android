package com.lodjinha

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.lodjinha.data.model.DataProduct
import com.lodjinha.ui.home.HomeModel
import org.junit.runner.RunWith
import com.lodjinha.ui.main.MainActivity
import org.junit.Rule
import org.junit.Before
import org.junit.Test
import android.support.test.espresso.matcher.ViewMatchers
import android.support.v7.widget.RecyclerView
import com.lodjinha.data.model.DataCategory
import com.lodjinha.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_home.*


@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @Rule @JvmField
    val mActivityRule = ActivityTestRule(MainActivity::class.java)
    var dataProduct: DataProduct? = null
    var dataCategory: DataCategory? = null

       @Before
    @Throws(Exception::class)
    fun setUp() {
        val model = HomeModel()
        dataProduct = model.getBestSellerProduct()
        dataCategory = model.getAllCategories()
    }

    @Test
    fun selectItemViewProduct() {
        val fragment = mActivityRule.activity.supportFragmentManager.fragments.get(0)
        val id = (fragment as HomeFragment).recycler_best_seller.id
        onView(ViewMatchers.withId(id)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(1000)
    }

    @Test
    fun selectItemViewCategory() {
        val fragment = mActivityRule.activity.supportFragmentManager.fragments.get(0)
        val id = (fragment as HomeFragment).recycler_view_categories.id
        onView(ViewMatchers.withId(id)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(1000)
    }

}