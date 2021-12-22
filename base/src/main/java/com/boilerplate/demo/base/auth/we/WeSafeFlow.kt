package com.boilerplate.demo.base.auth.we

import kotlinx.coroutines.flow.AbstractFlow
import kotlinx.coroutines.flow.FlowCollector

class WeSafeFlow<T>(private val block: suspend FlowCollector<T>.() -> Unit) : AbstractFlow<T>() {
  override suspend fun collectSafely(collector: FlowCollector<T>) {
    collector.block()
  }
}