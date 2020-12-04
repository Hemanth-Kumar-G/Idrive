package com.hemanth.idrive.presentation.webUi

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.hemanth.idrive.R
import com.hemanth.idrive.base.BaseActivity
import com.hemanth.idrive.common.Constants
import com.hemanth.idrive.databinding.ActivityWebBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 *<h1>WebActivity</h1>
 * @author : Hemanth     hemanthappu006@gmail.com
 * @version : 1.0
 */
@AndroidEntryPoint
class WebActivity : BaseActivity() {

    private lateinit var mBinding: ActivityWebBinding
    private val viewModel: WebViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_web)
        mBinding.viewModel = viewModel
        parsePassedData()
    }

    /**
     * <h2>parsePassedData</h2>
     * method to get data from previous activity
     */
    private fun parsePassedData() {
        val data = intent.getStringExtra(Constants.ANSWER_LINK)
        initWebView(data)
    }

    /**
     * <h2>initWebView</h2>
     * <P>Enable javaScript  and webViewClient to webView.
     * showing progress while web view loading
     * </P>
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(url: String?) {
        mBinding.webView.settings.javaScriptEnabled = true
        mBinding.webView.settings.builtInZoomControls = true
        mBinding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                viewModel.loading.set(true)
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                viewModel.loading.set(false)
            }
        }
        url?.let { mBinding.webView.loadUrl(it) }
    }
}