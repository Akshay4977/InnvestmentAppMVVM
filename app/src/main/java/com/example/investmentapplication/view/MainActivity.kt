package com.example.investmentapplication.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import com.example.investmentapplication.R
import com.example.investmentapplication.utils.Constants

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showSplash()
    }

    private fun showSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }, Constants.SPLASH_TIME)
    }
}
