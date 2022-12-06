package com.sajiiidali.rescue1122.vehicle.dashboard

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.sajiiidali.rescue1122.vehicle.dashboard.databinding.MainDashboardBinding
import com.sajiiidali.rescue1122.vehicle.dashboard.viewModel.WebViewModel


class MainDashBoard :Fragment() {

    private lateinit var pageViewModel: WebViewModel
    private var _binding: MainDashboardBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = MainDashboardBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webView = binding?.showBillWebView!!
        progressBar = binding?.progressCircular!!
        mContext = requireContext()

        pageViewModel = ViewModelProvider(this).get(WebViewModel::class.java)
        pageViewModel.setWebView(webView!!)

        val settingPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        SettingsFragment.userName = settingPreferences.getString("userName","")!!
        SettingsFragment.password = settingPreferences.getString("password","")!!

        if (SettingsFragment.userName!!.isEmpty()){
            val direction = MainDashBoardDirections.actionMainDashBoardToSaveUserNamePassword()
            findNavController().navigate(direction)
        }

        webView!!.webViewClient = ShowWebView.MyWebViewClient(
            binding?.progressCircular!!,
            SettingsFragment.userName!!,
            SettingsFragment.password!!
        )

        val myWebSettings = webView!!.settings
        myWebSettings.javaScriptEnabled = true
        myWebSettings.loadWithOverviewMode = true
        myWebSettings.useWideViewPort = true
        myWebSettings.builtInZoomControls = true

        webView!!.loadUrl("https://punjab.rescue1122.org/dashboard")

        webView!!.setOnKeyListener(View.OnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK && webView!!.canGoBack()) {
                webView!!.goBack()
                return@OnKeyListener true
            }
            false
        })

        webView!!.setDownloadListener { url, _, _, _, _ ->
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object{
        @SuppressLint("StaticFieldLeak")
        var webView:WebView? = null
        @SuppressLint("StaticFieldLeak")
        var progressBar:ProgressBar? =  null
        @SuppressLint("StaticFieldLeak")
        var mContext:Context? =  null

        fun reloadWeb(){
            webView?.reload()

            val settingPreferences = PreferenceManager.getDefaultSharedPreferences(mContext!!)
            SettingsFragment.userName = settingPreferences.getString("userName","")!!
            SettingsFragment.password = settingPreferences.getString("password","")!!
            webView!!.webViewClient = ShowWebView.MyWebViewClient(
                progressBar!!,
                SettingsFragment.userName!!,
                SettingsFragment.password!!
            )
        }
    }

}