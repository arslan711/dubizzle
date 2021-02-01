package com.dubizzle.assignment.repository.remote

import com.dubizzle.assignment.repository.local.pojo.Result
import retrofit2.http.GET

interface DubizzleWebApisInterface {

    @GET("default/dynamodb-writer")
    suspend fun getDubizzlesData(): Result

}
