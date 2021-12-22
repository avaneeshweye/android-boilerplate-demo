package com.boilerplate.demo.service

import com.boilerplate.demo.base.auth.service.ApiResponse
import com.boilerplate.demo.model.ItemEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET


interface ApiServices {
    @GET("/v2/beers")
    fun getItems(): Flow<ApiResponse<List<ItemEntity>>>

}
