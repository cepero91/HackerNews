package com.infinitumcode.hackernews.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.infinitumcode.hackernews.R

class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Handler(Looper.myLooper()!!).postDelayed({
            findNavController().navigate(R.id.action_nav_splash_to_nav_main)
        }, 3000)
    }
}