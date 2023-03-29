package com.example.innobuzzassignment

import retrofit2.http.GET
import retrofit2.Call

interface Api {
    //Get request contains end point of the URL
    @GET("posts")
    fun getInstances():Call<List<Model>>
}