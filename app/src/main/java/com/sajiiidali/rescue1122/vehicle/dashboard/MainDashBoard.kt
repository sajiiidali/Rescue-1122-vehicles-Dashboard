package com.sajiiidali.rescue1122.vehicle.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

        pageViewModel = ViewModelProvider(this).get(WebViewModel::class.java)
        pageViewModel.setWebView(binding?.showBillWebView!!)

        val settingPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        SettingsFragment.userName = settingPreferences.getString("userName","")!!
        SettingsFragment.password = settingPreferences.getString("passWord","")!!

        binding?.showBillWebView!!.webViewClient = ShowWebView.MyWebViewClient(
            binding?.progressCircular!!,
            SettingsFragment.userName!!,
            SettingsFragment.password!!
        )

        val myWebSettings = binding?.showBillWebView!!.settings
        myWebSettings.javaScriptEnabled = true
        myWebSettings.loadWithOverviewMode = true
        myWebSettings.useWideViewPort = true
        myWebSettings.builtInZoomControls = true

        binding?.showBillWebView!!.loadUrl("https://punjab.rescue1122.org/dashboard")

        binding?.showBillWebView!!.setOnKeyListener(View.OnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK && binding?.showBillWebView!!.canGoBack()) {
                binding?.showBillWebView!!.goBack()
                return@OnKeyListener true
            }
            false
        })

        binding?.showBillWebView!!.setDownloadListener { url, _, _, _, _ ->
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

    }








    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}