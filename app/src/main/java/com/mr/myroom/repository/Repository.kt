package com.mr.myroom.repository

import com.mr.myroom.model.ListResponseModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {

    suspend fun getData(): Flow<Response<ListResponseModel>>
}