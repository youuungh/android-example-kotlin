package com.example.retrofit2

import retrofit2.Call
import retrofit2.http.GET

interface UpbitAPI {
    @GET("v1/market/all")

    fun getAllCoin(): Call<List<Coin>>
}