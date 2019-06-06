package com.lodjinha.ui.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import com.lodjinha.data.model.Banner
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lodjinha.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.banner_slider.view.*


class SliderBannerAdapter(val context: Context, val banners: ArrayList<Banner>) : PagerAdapter() {

    override fun isViewFromObject(view: View, p1: Any): Boolean {
        return view == p1
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.banner_slider, null)
        Picasso.with(context).load(banners.get(position).urlImagem).fit().into(view.imgBanner)
        val viewPager = container as ViewPager
        viewPager.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, p1: Any) {
        val viewPager = container as ViewPager
        val view = p1 as View
        viewPager.removeView(view)
    }

    override fun getCount(): Int {
        return banners.size
    }
}