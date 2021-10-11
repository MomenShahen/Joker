package com.messages.jokes.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.messages.jokes.R
import com.messages.jokes.databinding.ActivitySplashBinding
import com.messages.jokes.model.Status
import com.messages.jokes.utils.DataPreference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    lateinit var splashBinding: ActivitySplashBinding
    val viewModel: PreferenceViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        viewModel.getStoredTime()
        viewModel.times.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d("TAGSP", "onCreate: "+ it?.data!!)
                    splashBinding.timesOpenedTxt.text =
                        getString(R.string.times_app_opened) + it.data
                    val time = it.data + 1
                    viewModel.setStoredTime(time)
                    Log.d("TAGSP", "onCreate: "+ time)
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {

                }

            }
        })

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