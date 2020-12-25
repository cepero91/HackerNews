package com.infinitumcode.hackernews.ui.main.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.infinitumcode.hackernews.ui.main.handler.HitItemListener
import com.infinitumcode.hackernews.ui.main.model.HitItem
import com.infinitumcode.hackernews.ui.main.viewholder.HitItemViewHolder

class HitListAdapter(private val listener: HitItemListener) :
    PagingDataAdapter<HitItem, RecyclerView.ViewHolder>(HIT_ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HitItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val hitItem = getItem(position)
        hitItem?.let {
            (holder as HitItemViewHolder).bind(it, listener)
        }
    }

    companion object {
        private val HIT_ITEM_COMPARATOR = object : DiffUtil.ItemCallback<HitItem>() {
            override fun areItemsTheSame(oldItem: HitItem, newItem: HitItem): Boolean {
                return (oldItem.objectId == newItem.objectId)
            }

            override fun areContentsTheSame(oldItem: HitItem, newItem: HitItem): Boolean =
                oldItem == newItem
        }
    }
}
