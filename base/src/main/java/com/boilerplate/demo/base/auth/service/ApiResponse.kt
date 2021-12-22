package com.boilerplate.demo.base.auth.service

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import retrofit2.Response

sealed class ApiResponse<T> {
    companion object {

        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(
                Error.Unknown(statusCode = -1, message = error.message ?: "Unknown error")
            )
        }


        fun <T> create(response: Response<T>): ApiResponse<T> {
            if (response.isSuccessful) {
                val body = response.body()
                val headers = response.headers()
                if (body == null || response.code() == 204) {
                    return ApiEmptyResponse()
                } else {
                    return ApiSuccessResponse(
                        body,
                        headers
                    )
                }
            } else {
                val msg = response.errorBody()?.string()
                val gson = GsonBuilder().setPrettyPrinting().create()
                val fromJson = gson.fromJson(msg, ErrorResponse::class.java)
                return ApiErrorResponse(Error.fromErrorResponse(response.code(), fromJson))
            }
        }
    }
}


class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(
    val body: T?,
    val headers: okhttp3.Headers
) : ApiResponse<T>()

data class ApiErrorResponse<T>(
    val error: Error?,
) : ApiResponse<T>()


data class ErrorResponse(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("message")
    var errorMessage: String? = null,

    @SerializedName("status")
    var statusCode: Int = 0,

    @SerializedName("type")
    var type: String? = null,

    @SerializedName("code")
    var code: Int = 0,

    @SerializedName("r_id")
    var requestId: String? = null
)

sealed class Error {
    data class SessionExpire(val statusCode: Int) : Error()
    data class Conflict(val code: Int, val statusCode: Int, val message: String?) : Error()
    data class NotFound(val statusCode: Int, val message: String?) : Error()
    data class Unknown(val statusCode: Int, val message: String?) : Error()
    companion object {
        fun fromErrorResponse(errorCode: Int, error: ErrorResponse): Error {
            return when (errorCode) {
                404 -> NotFound(error.statusCode, error.errorMessage)
                409 -> Conflict(error.code, error.statusCode, error.errorMessage)
                421 -> SessionExpire(error.statusCode)
                else -> Unknown(error.statusCode, error.errorMessage)
            }

        }
    }
}