package com.boilerplate.demo.base.auth.network

import com.boilerplate.demo.base.auth.service.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

inline fun <ResultType> networkBoundResource(
  crossinline fetchFromRemote: () -> Flow<ApiResponse<ResultType>>,
  crossinline processRemoteResponse: (response: ApiSuccessResponse<ResultType>) -> Unit = { },
  crossinline onFetchFailed: (errorBody: Error?) -> Unit = { _: Error? -> Unit }
) =
    flow<Resource<ResultType>> {

        emit(Resource.loading(null))

        fetchFromRemote().collect { apiResponse ->
            when (apiResponse) {
                is ApiSuccessResponse -> {
                    processRemoteResponse(apiResponse)
                    emit(Resource.success(apiResponse.body))
                }
                is ApiErrorResponse -> {
                    onFetchFailed(apiResponse.error)
                    emit(
                        Resource.error(
                            apiResponse.error,
                            null
                        )
                    )
                }
                is ApiEmptyResponse -> emit(
                    Resource.success(null)
                )
            }
        }

    }