package com.boilerplate.demo.base.auth.network

import com.boilerplate.demo.base.auth.service.*
import com.boilerplate.demo.base.auth.we.WeFlow
import com.boilerplate.demo.base.auth.we.WeSafeFlow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.flow
import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
public fun <T> flow(@BuilderInference block: suspend FlowCollector<T>.() -> Unit): Flow<T> = WeSafeFlow(block)

inline fun <ResultType> networkBoundResource(
  crossinline fetchFromRemote: () -> WeFlow<ApiResponse<ResultType>>,
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