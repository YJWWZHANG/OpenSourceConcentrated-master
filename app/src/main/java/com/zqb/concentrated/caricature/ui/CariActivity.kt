package com.zqb.concentrated.caricature.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.zqb.concentrated.R
import com.zqb.concentrated.news.view.NewsActivity
import com.zqb.concentrated.weather.view.WeatherActivity
import kotlinx.android.synthetic.main.activity_cari.*
import kotlinx.android.synthetic.main.navigation.*

class CariActivity : AppCompatActivity(){

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

        initView()
        initEvent()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun initView() {
        setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.caricature)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        drawer_layout.setScrimColor(Color.TRANSPARENT)
        navigation_view.setCheckedItem(R.id.action_caricature)
    }

    private fun initEvent() {
        drawer_layout.addDrawerListener(object : DrawerLayout.DrawerListener{
            override fun onDrawerStateChanged(newState: Int) {}

            override fun onDrawerSlide(drawerView: View?, slideOffset: Float) {
                drawer_layout.getChildAt(0).translationX = drawerView!!.measuredWidth * slideOffset
            }

            override fun onDrawerClosed(drawerView: View?) {}

            override fun onDrawerOpened(drawerView: View?) {}

        })
        navigation_view.setNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.action_news -> NewsActivity.launch(this)
                R.id.action_book -> { }
                R.id.action_caricature -> { }
                R.id.action_video_tv -> { }
                R.id.action_music -> { }
                R.id.action_photo -> { }
                R.id.action_weather -> WeatherActivity.launch(this)
            }
            true
        }
    }

}
