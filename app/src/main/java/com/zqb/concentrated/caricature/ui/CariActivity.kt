package com.zqb.concentrated.caricature.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View

import com.zqb.concentrated.R
import kotlinx.android.synthetic.main.activity_cari.*
import kotlinx.android.synthetic.main.navigation.*

class CariActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    companion object {
        fun launch(activity : Activity){
            val intent = Intent(activity, CariActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cari)

        setSupportActionBar(toolbar)
        initDrawrtLayout()
    }

    private fun initDrawrtLayout() {
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        drawer_layout.setScrimColor(Color.TRANSPARENT)
        drawer_layout.addDrawerListener(object : DrawerLayout.DrawerListener{
            override fun onDrawerStateChanged(newState: Int) {
            }

            override fun onDrawerSlide(drawerView: View?, slideOffset: Float) {
                val content = drawer_layout.getChildAt(0)
                content.translationX = drawer_layout.width * slideOffset
            }

            override fun onDrawerClosed(drawerView: View?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDrawerOpened(drawerView: View?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        navigation_view.setCheckedItem(R.id.action_caricature)
        navigation_view.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }

}
