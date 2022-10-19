package com.rich.diexercise.network

import com.rich.diexercise.model.ResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface RestfulAPI {
    @GET("products.json")
    fun getALlProducts() : Call<List<ResponseItem>>
}