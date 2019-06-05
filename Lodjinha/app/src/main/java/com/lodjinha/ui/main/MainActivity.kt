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
import java.util.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    internal var active: Fragment? = null

    private lateinit var mFragments: EnumMap<FragmentTypes, Fragment>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setUpDrawerMenu()
        configUi()
    }

    fun configUi() {
        mFragments = EnumMap(FragmentTypes::class.java)
        changeFragment(getHome())
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
                if (item.isChecked()) shouldChange = false;
                else {
                    fragment = getHome();
                    item.setChecked(true);
                }
            }
            R.id.nav_about -> {
                if (item.isChecked()) shouldChange = false;
                else {
                    fragment = getAbout();
                    item.setChecked(true);
                }
            }
        }

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

    private fun getHome(): Fragment {
        if (!mFragments.containsKey(FragmentTypes.HOME)) {
            mFragments.put(FragmentTypes.HOME, FragmentTypes.HOME.getFragment())
            mFragments.get(FragmentTypes.HOME)!!.setRetainInstance(true)
        }
        return mFragments.get(FragmentTypes.HOME)!!
    }

    private fun getAbout(): Fragment {
        if (!mFragments.containsKey(FragmentTypes.ABOUT)) {
            mFragments.put(FragmentTypes.ABOUT, FragmentTypes.ABOUT.getFragment())
            mFragments.get(FragmentTypes.ABOUT)!!.setRetainInstance(true)
        }
        return mFragments.get(FragmentTypes.ABOUT)!!
    }


    enum class FragmentTypes {
        HOME {
            override fun getFragment(): Fragment {
                return HomeFragment.newInstance()
            }
        },
        ABOUT {
            override fun getFragment(): Fragment {
                return AboutFragment.newInstance()
            }
        };

        abstract fun getFragment(): Fragment
    }
}
