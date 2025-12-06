package com.epsilonvpn.data.api

import com.epsilonvpn.data.model.User
import com.epsilonvpn.data.model.VPNConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("login")
    fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): Call<User>

    @GET("getConfig")
    fun getConfig(@Query("username") username: String): Call<VPNConfig>
}
