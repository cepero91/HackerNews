package com.infinitumcode.hackernews.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.infinitumcode.hackernews.R
import com.infinitumcode.hackernews.databinding.FragmentMainBinding
import com.infinitumcode.hackernews.utils.DEFAULT_QUERY
import com.wada811.databinding.dataBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModel()
    private val binding: FragmentMainBinding by dataBinding()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        obsHits()
    }

    private fun obsHits() {
        viewModel.getHitsByDate(DEFAULT_QUERY).observe(viewLifecycleOwner, {
            Log.e("TAG", "$it")
        })
    }

}