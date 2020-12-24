package com.infinitumcode.hackernews.domain.usecase

import com.infinitumcode.hackernews.domain.repository.HackerNewsRepository

class RemoveHitUseCase(private val repository: HackerNewsRepository) {
    suspend operator fun invoke(hitObjectId: String) = repository.removeItemById(hitObjectId)
}