package com.boilerplate.demo

import com.boilerplate.demo.base.auth.service.Resource
import com.boilerplate.demo.repo.AppRepository
import com.boilerplate.demo.service.ApiServices
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainServiceTest : BaseTest() {


    private lateinit var apiServices: ApiServices
    private lateinit var repo: AppRepository
    private val testDispatcher = TestCoroutineDispatcher()


    @Before
    fun createRepo() {
        apiServices = retrofitClient.create(ApiServices::class.java)
        repo = AppRepository()
        repo.ioDispatcher = testDispatcher
        repo.apiServices = apiServices
    }


    @ExperimentalCoroutinesApi
    @Test
    fun itemList() = runBlocking {
        enqueueResponse("main/list_item.json")
        repo.getItems().collect {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    MatcherAssert.assertThat(it.data.isNullOrEmpty(), `is`(false))
                }
                Resource.Status.ERROR -> {

                }
                Resource.Status.LOADING -> {


                }
            }
        }
    }
}
