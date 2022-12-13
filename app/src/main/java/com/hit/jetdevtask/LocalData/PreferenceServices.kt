package com.hit.jetdevtask.LocalData

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class PreferenceServices private constructor() {

    companion object {
        private val sharePref = PreferenceServices()
        private lateinit var sharedPreferences: SharedPreferences

        private val user_id = "user_id"
        private val name = "name"

        fun getInstance(context: Context): PreferenceServices {
            if (!::sharedPreferences.isInitialized) {
                synchronized(PreferenceServices::class.java) {
                    if (!::sharedPreferences.isInitialized) {
                        sharedPreferences =
                            context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
                    }
                }
            }
            return sharePref
        }
    }

    val getUserid: String?
        get() = sharedPreferences.getString(user_id, "")

    fun setUserid(id: String) {
        sharedPreferences.edit()
            .putString(user_id, id)
            .apply()
    }

    val getname: String?
        get() = sharedPreferences.getString(name, "")

    fun setname(namee: String) {
        sharedPreferences.edit()
            .putString(name, namee)
            .apply()
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

}
