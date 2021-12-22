package com.boilerplate.demo.repo

import com.boilerplate.demo.base.auth.network.networkBoundResource
import com.boilerplate.demo.base.auth.service.Resource
import com.boilerplate.demo.base.ui.BaseRepo
import com.boilerplate.demo.model.ItemEntity
import com.boilerplate.demo.service.ApiServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

open class AppRepository @Inject constructor(): BaseRepo() {

    @Inject
    lateinit var apiServices: ApiServices

    fun getItems(): Flow<Resource<List<ItemEntity>>> {
        return networkBoundResource(
            fetchFromRemote = {
                apiServices.getItems()
            },
            processRemoteResponse = {


            },
            onFetchFailed = { _ -> }
        ).flowOn(ioDispatcher)

    }

}
