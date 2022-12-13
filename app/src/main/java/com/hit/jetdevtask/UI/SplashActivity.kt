package com.hit.jetdevtask.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.hit.jetdevtask.LocalData.PreferenceServices
import com.hit.jetdevtask.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        handler.postDelayed({

            if (PreferenceServices.getInstance(this).getUserid!!.isNotEmpty()) {
                val intent = Intent(this, ShowDataActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, 2000)
    }
}