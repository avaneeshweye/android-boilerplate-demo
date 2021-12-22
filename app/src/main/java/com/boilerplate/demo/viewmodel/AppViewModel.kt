package com.boilerplate.demo.viewmodel

import androidx.lifecycle.*
import com.boilerplate.demo.base.auth.service.Resource
import com.boilerplate.demo.base.ui.BaseViewModel
import com.boilerplate.demo.model.ItemResponse
import com.boilerplate.demo.model.map
import com.boilerplate.demo.repo.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repo: AppRepository) : BaseViewModel() {
  
  
  private var _itemResponseClick: MutableLiveData<ItemResponse> = MutableLiveData()
  var itemResponseClick: LiveData<ItemResponse> = _itemResponseClick
  
  private var _list: MutableLiveData<Resource<List<ItemResponse>>> = MutableLiveData()
  var list: LiveData<Resource<List<ItemResponse>>> = _list
  
  fun getItemList() {
    list = repo.getItems()
      .asLiveData(viewModelScope.coroutineContext).map {
        val list = ArrayList<ItemResponse>()
        it.data?.forEach {
          list.add(it.map())
        }
        when (it.status) {
          Resource.Status.SUCCESS -> Resource.success(list.toList())
          Resource.Status.ERROR -> Resource.error(it.errorResponse)
          Resource.Status.LOADING -> Resource.loading()
        }
        
      }
  }
  
  
  fun itemClick(itemResponse: ItemResponse) {
    _itemResponseClick.value = itemResponse
  }
  
}