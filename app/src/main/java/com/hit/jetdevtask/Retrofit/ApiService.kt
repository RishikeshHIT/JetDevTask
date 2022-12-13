package com.Kotlintest.ApiProject.Retrofit

import com.hit.labchoiceuser.Model.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    fun getLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>

}