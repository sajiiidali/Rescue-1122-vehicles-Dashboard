package com.sajiiidali.rescue1122.vehicle.dashboard

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import androidx.lifecycle.ViewModelProvider
import com.sajiiidali.rescue1122.vehicle.dashboard.viewModel.WebViewModel
import android.content.Intent
import android.graphics.drawable.AnimationDrawable


class ShowWebView : Fragment(R.layout.show_web_view){
    private lateinit var pageViewModel: WebViewModel
    var myWebView: WebView? = null
    private lateinit var rocketAnimation: AnimationDrawable

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        myWebView = view.findViewById(R.id.showBillWebView)
        pageViewModel = ViewModelProvider(this).get(WebViewModel::class.java)
        pageViewModel.setWebView(myWebView!!)

        val settingPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        SettingsFragment.userName = settingPreferences.getString("userName","")!!
        SettingsFragment.password = settingPreferences.getString("passWord","")!!

        val args = ShowWebViewArgs.fromBundle(requireArguments())


        val progressBar = view.findViewById<ProgressBar>(R.id.progress_circular)

        myWebView?.webViewClient = MyWebViewClient(progressBar, SettingsFragment.userName!!, SettingsFragment.password!!)

        val myWebSettings = myWebView?.settings
        myWebSettings?.javaScriptEnabled = true
        myWebSettings?.loadWithOverviewMode = true
        myWebSettings?.useWideViewPort = true
        myWebSettings?.builtInZoomControls = true

        myWebView?.loadUrl(args.url!!)

        myWebView?.setOnKeyListener(View.OnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK && myWebView?.canGoBack()!!) {
                myWebView?.goBack()
                return@OnKeyListener true
            }
            false
        })

        myWebView?.setDownloadListener { url, _, _, _, _ ->
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        pageViewModel.getWebView().observe(requireActivity(), { customWebView ->
            if (customWebView != null) {
                myWebView = customWebView
            }
        })
    }
    class MyWebViewClient(progressBar: ProgressBar, userName: String, password: String) : WebViewClient(){
        private val myProgressBar = progressBar
        private val userNameOf = userName
        private val passwordOf = password

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            myProgressBar.visibility = View.VISIBLE
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            myProgressBar.visibility = View.GONE
            if (url == "https://punjab.rescue1122.org/login"){
                view?.loadUrl("javascript:(function(){  document.getElementsByTagName('input')[1].value ='$userNameOf';})();")
                view?.loadUrl("javascript:(function(){ document.getElementsByTagName('input')[2].value ='$passwordOf';})();")
            }


            super.onPageFinished(view, url)
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url!!)
            return true
        }
    }


}