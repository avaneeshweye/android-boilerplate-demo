package com.boilerplate.demo.base.auth.service

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val errorResponse: Error?
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(errorResponse: Error?, data: T? = null): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                errorResponse
            )
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}