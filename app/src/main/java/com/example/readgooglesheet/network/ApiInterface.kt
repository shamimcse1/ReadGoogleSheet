package com.example.readgooglesheet.network

import com.example.readgooglesheet.helper.ApiEndPoint
import com.example.readgooglesheet.model.DataModel
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET(ApiEndPoint)
    fun fetchData(): Call<DataModel>

}