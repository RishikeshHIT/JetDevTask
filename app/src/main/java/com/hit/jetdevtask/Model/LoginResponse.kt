package com.hit.labchoiceuser.Model

import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("errorCode")
    var errorCode: String? = null

    @SerializedName("errorMessage")
    var errorMessage: String? = null

    @SerializedName("data")
    var loginData: LoginData? = null
}