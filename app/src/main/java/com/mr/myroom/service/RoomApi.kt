package com.mr.myroom.service

import com.mr.myroom.model.ListResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface RoomApi {
    @GET("users")
    suspend fun getData(): Response<ListResponseModel>
}