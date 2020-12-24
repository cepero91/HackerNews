package com.infinitumcode.hackernews.ui.main.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.infinitumcode.hackernews.R
import com.infinitumcode.hackernews.databinding.ItemHitBinding
import com.infinitumcode.hackernews.ui.main.handler.HitItemListener
import com.infinitumcode.hackernews.ui.main.model.HitItem

class HitItemViewHolder(val binding: ItemHitBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(hitItem: HitItem, listener: HitItemListener?) {
        with(binding) {
            this.model = hitItem
            this.listener = listener
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup): HitItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_hit, parent, false)
            val binding = ItemHitBinding.bind(view)
            return HitItemViewHolder(binding)
        }
    }
}