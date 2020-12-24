package com.infinitumcode.hackernews.ui.main

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.infinitumcode.hackernews.domain.usecase.RemoveHitUseCase
import com.infinitumcode.hackernews.domain.usecase.LocalListHitUseCase
import com.infinitumcode.hackernews.ui.main.mapper.MapHitToItem
import com.infinitumcode.hackernews.ui.main.model.HitItem
import com.infinitumcode.hackernews.utils.DEFAULT_QUERY
import com.infinitumcode.hackernews.utils.SingleLiveEvent
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(
    private val localListHitUseCase: LocalListHitUseCase,
    private val removeHitUseCase: RemoveHitUseCase,
    private val mapHitToItem: MapHitToItem
) : ViewModel() {

    private val _itemRemoved: SingleLiveEvent<Boolean> by lazy { SingleLiveEvent() }
    val itemRemoved get() = _itemRemoved

    fun allHits(): LiveData<PagingData<HitItem>> {
        return localListHitUseCase.invoke(DEFAULT_QUERY).map { pagingData ->
            pagingData.map {
                mapHitToItem.map(it)
            }
        }.cachedIn(viewModelScope).asLiveData()
    }

    fun removeHitItem(hitObjectId: String) {
        viewModelScope.launch {
            _itemRemoved.postValue(removeHitUseCase.invoke(hitObjectId))
        }
    }

}