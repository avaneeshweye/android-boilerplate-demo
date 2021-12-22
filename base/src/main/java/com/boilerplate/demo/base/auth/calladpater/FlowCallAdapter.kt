package com.boilerplate.demo.base.auth.calladpater

import com.boilerplate.demo.base.auth.service.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.awaitResponse
import java.lang.reflect.Type

class FlowCallAdapter<T>(
    private val responseType: Type
) : CallAdapter<T, Flow<ApiResponse<T>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<T>): Flow<ApiResponse<T>> = flow {
        val response = call.awaitResponse()
        emit(ApiResponse.create(response))
    }.catch { error ->
        emit(ApiResponse.create(error))
    }
}