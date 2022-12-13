package com.hit.jetdevtask.UI

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import com.Kotlintest.ApiProject.Retrofit.ApiService
import com.Kotlintest.ApiProject.Retrofit.RetrofitBase
import com.hit.jetdevtask.LocalData.PreferenceServices
import com.hit.jetdevtask.R
import com.hit.labchoiceuser.Model.LoginData
import com.hit.labchoiceuser.Model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var btn_login: Button
    lateinit var edtxt_username: EditText
    lateinit var edtxt_password: EditText
    var loginData: LoginData? = null
    val api = RetrofitBase().getClient()!!.create(ApiService::class.java)
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressDialog = ProgressDialog(this)
        progressDialog!!.setCancelable(false)
        inita()
    }

    private fun inita() {
        edtxt_username = findViewById(R.id.edtxt_username)
        edtxt_password = findViewById(R.id.edtxt_password)

        btn_login = findViewById(R.id.btn_login)

        btn_login.setOnClickListener {
            if (edtxt_username.text.isEmpty()) {
                Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT)
                    .show()
            } else if (edtxt_password.text.isEmpty()) {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT)
                    .show()
            } else {
                callLoginApi()
            }
        }

    }


    private fun callLoginApi() {

        showProgressDialog()
        val call: Call<LoginResponse> =
            api.getLogin(
                edtxt_username.text.toString().trim(),
                edtxt_password.text.toString().trim()
            )
        call.enqueue(object : Callback<LoginResponse?> {
            override fun onResponse(
                call: Call<LoginResponse?>,
                response: Response<LoginResponse?>
            ) {
                if (response.body() != null) {
                    val resource = response.body()
                    if (resource != null) {
                        if (resource.errorCode.equals("00")) {
                            hideProgressDialog()
                            loginData = resource.loginData


                            // store local data
                            PreferenceServices.getInstance(this@MainActivity)
                                .setUserid(loginData!!.userId!!)
                            PreferenceServices.getInstance(this@MainActivity)
                                .setname(loginData!!.userName!!)

                            val intent = Intent(this@MainActivity, ShowDataActivity::class.java)
                                .putExtra("userid", loginData!!.userId)
                                .putExtra("name", loginData!!.userName)
                            startActivity(intent)

                        } else {
                            hideProgressDialog()
                            Toast.makeText(
                                this@MainActivity,
                                "" + resource.errorMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        hideProgressDialog()
                    }
                } else {
                    hideProgressDialog()
                }
            }

            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                call.cancel()
                hideProgressDialog()
            }
        })

    }


    private fun showProgressDialog() {
        if (!progressDialog!!.isShowing) {
            progressDialog!!.show()
            progressDialog!!.setContentView(R.layout.item_loader)
            Objects.requireNonNull<Window>(progressDialog!!.window)
                .setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun hideProgressDialog() {
        if (progressDialog!!.isShowing) progressDialog!!.dismiss()
    }
}