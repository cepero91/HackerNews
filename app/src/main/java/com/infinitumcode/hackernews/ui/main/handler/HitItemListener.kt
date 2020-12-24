package com.infinitumcode.hackernews.ui.main.handler

import com.infinitumcode.hackernews.ui.main.model.HitItem

interface HitItemListener {
    fun onHitClick(item: HitItem)
}