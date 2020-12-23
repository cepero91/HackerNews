package com.infinitumcode.hackernews.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.infinitumcode.hackernews.domain.usecase.SearchByDateUseCase
import com.infinitumcode.hackernews.ui.main.mapper.MapHitToItem
import com.infinitumcode.hackernews.ui.main.model.HitItem
import kotlinx.coroutines.flow.map

class MainViewModel(
    private val searchByDateUseCase: SearchByDateUseCase,
    private val mapHitToItem: MapHitToItem
) : ViewModel() {

    fun getHitsByDate(query: String?): LiveData<PagingData<HitItem>> {
        return searchByDateUseCase.invoke(query).map { pagingData ->
            pagingData.map {
                mapHitToItem.map(it)
            }
        }.cachedIn(viewModelScope).asLiveData()
    }
}