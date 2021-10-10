package com.messages.jokes.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.messages.jokes.R
import com.messages.jokes.databinding.ActivitySplashBinding
import com.messages.jokes.utils.DataPreference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    lateinit var splashBinding: ActivitySplashBinding
    lateinit var sharedPreferences: DataPreference

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)
        splashBinding.timesOpenedTxt.text = getString(R.string.times_app_opened) + sharedPreferences.getStoredTag().toString()
        sharedPreferences.setStoredTag(sharedPreferences.getStoredTag()+1)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3000 is the delayed time in milliseconds.
    }
}