package com.boilerplate.demo.base.ui

import com.boilerplate.demo.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

open class BaseRepo {

    @Inject
    @IoDispatcher
    lateinit var ioDispatcher: CoroutineDispatcher

}
