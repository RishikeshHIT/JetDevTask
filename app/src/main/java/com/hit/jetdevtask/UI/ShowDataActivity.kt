package com.hit.jetdevtask.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.hit.jetdevtask.LocalData.PreferenceServices
import com.hit.jetdevtask.R

class ShowDataActivity : AppCompatActivity() {

    lateinit var txt_userid: TextView
    lateinit var txt_username: TextView
    lateinit var txt_logout: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)
        inita()
    }

    private fun inita() {
        txt_logout = findViewById(R.id.txt_logout)
        txt_logout.setOnClickListener {
            if (!PreferenceServices.getInstance(this@ShowDataActivity).getUserid.equals("")) {
                PreferenceServices.getInstance(this@ShowDataActivity).setUserid("")
                Toast.makeText(this@ShowDataActivity, "Logout Successfully!", Toast.LENGTH_SHORT)
                    .show()
                startActivity(
                    Intent(this@ShowDataActivity, MainActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                )
            }
        }
        txt_userid = findViewById(R.id.txt_userid)
        txt_username = findViewById(R.id.txt_username)
        txt_userid.text = PreferenceServices.getInstance(this).getUserid
        txt_username.text = PreferenceServices.getInstance(this).getname
    }
}