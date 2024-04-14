package com.mr.myroom.repository

import com.mr.myroom.model.ListResponseModel
import com.mr.myroom.service.RoomApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val roomApi: RoomApi) : Repository {

    override suspend fun getData(): Flow<Response<ListResponseModel>> {
        return flow {
            emit(roomApi.getData())
        }.flowOn(Dispatchers.IO)
    }
}