package com.zqb.concentrated.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.zqb.concentrated.R
import com.zqb.concentrated.news.view.NewsActivity

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        startActivity(Intent(this, NewsActivity::class.java))
        finish()
    }
}
