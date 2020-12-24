package com.infinitumcode.hackernews.domain.usecase

import com.infinitumcode.hackernews.domain.repository.HackerNewsRepository

class LocalListHitUseCase(private val repository: HackerNewsRepository) {
    operator fun invoke(query: String?) = repository.allHits(query)
}