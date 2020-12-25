package com.infinitumcode.hackernews.ui.detail

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import com.infinitumcode.hackernews.R
import com.infinitumcode.hackernews.databinding.FragmentDetailHitBinding
import com.infinitumcode.hackernews.utils.EXTRA_STORY_ID
import com.wada811.databinding.dataBinding

class DetailHitFragment : Fragment(R.layout.fragment_detail_hit) {

    private val binding: FragmentDetailHitBinding by dataBinding()
    private var storyUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            storyUrl = it.getString(EXTRA_STORY_ID, "")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        with(binding) {
            wvHitDetail.settings.javaScriptEnabled = true
            wvHitDetail.webViewClient = object : WebViewClient(){
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    binding.pbLoading.isVisible = true
                    super.onPageStarted(view, url, favicon)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    binding.pbLoading.isVisible = false
                    super.onPageFinished(view, url)
                }
            }
           storyUrl?.let {
               wvHitDetail.loadUrl(it)
           }
            executePendingBindings()
        }
    }

}