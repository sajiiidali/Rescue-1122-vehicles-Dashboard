package com.sajiiidali.rescue1122.vehicle.dashboard

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager


class ShowWebView : Fragment(R.layout.show_web_view) {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myWebView = view.findViewById<WebView>(R.id.showBillWebView)

        val settingPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        SettingsFragment.userName = settingPreferences.getString("userName","")!!
        SettingsFragment.password = settingPreferences.getString("passWord","")!!

        val args = ShowWebViewArgs.fromBundle(requireArguments())
        val progressBar = view.findViewById<ProgressBar>(R.id.progress_circular)

        myWebView.webViewClient = MyWebViewClient(progressBar, SettingsFragment.userName!!, SettingsFragment.password!!)

        val myWebSettings = myWebView.settings
        myWebSettings.javaScriptEnabled = true
        myWebSettings.loadWithOverviewMode = true
        myWebSettings.useWideViewPort = true
        myWebSettings.builtInZoomControls = true

        myWebView.loadUrl(args.url!!)

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