package com.hit.labchoiceuser.Model

import com.google.gson.annotations.SerializedName

class LoginData {
    @SerializedName("userId")
    var userId: String? = null

    @SerializedName("userName")
    var userName: String? = null

    @SerializedName("isDeleted")
    var isDeleted = false
}