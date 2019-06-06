package com.lodjinha.ui.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.lodjinha.R
import com.lodjinha.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_layout.*

import android.support.v4.app.Fragment
import com.lodjinha.ui.about.AboutFragment


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setUpDrawerMenu()
        configUi()
    }

    fun configUi() {
        changeFragment(HomeFragment.newInstance())
        nav_view_menu.itemIconTintList = null
    }

    private fun setUpDrawerMenu() {
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view_menu.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        var shouldChange = true
        when (item.itemId) {
            R.id.nav_home -> {
                if (item.isChecked) shouldChange = false
                else {
                    fragment = HomeFragment.newInstance()
                }
            }
            R.id.nav_about -> {
                if (item.isChecked) shouldChange = false
                else {
                    fragment = AboutFragment.newInstance()
                }
            }
        }

        item.isChecked = true
        if (shouldChange) {
            changeFragment(fragment)
        }
        drawer_layout.closeDrawer(GravityCompat.START)

        return true
    }

    private fun changeFragment(fragment: Fragment?) {
        if (fragment != null) {
            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }
    }

}
