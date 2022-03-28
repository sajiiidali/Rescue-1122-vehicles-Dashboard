package com.sajiiidali.rescue1122.vehicle.dashboard

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.util.*

class PtsVehicles : DialogFragment(R.layout.pts_layout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button7 = view.findViewById<AppCompatButton>(R.id.bna7)
        val button8 = view.findViewById<AppCompatButton>(R.id.bna8)
        val button9 = view.findViewById<AppCompatButton>(R.id.bna9)
        val button21 = view.findViewById<AppCompatButton>(R.id.bna21)
        val button22 = view.findViewById<AppCompatButton>(R.id.bna22)

        button7.setOnClickListener {
            val direction = PtsVehiclesDirections.actionPtsVehiclesToShowWebView("https://punjab.rescue1122.org/dashboard/vehicle/1338/edit")
            findNavController().navigate(direction)
        }
        button8.setOnClickListener {
            val direction = PtsVehiclesDirections.actionPtsVehiclesToShowWebView("https://punjab.rescue1122.org/dashboard/vehicle/1305/edit")
            findNavController().navigate(direction)
        }
        button9.setOnClickListener {
            val direction = PtsVehiclesDirections.actionPtsVehiclesToShowWebView("https://punjab.rescue1122.org/dashboard/vehicle/1310/edit")
            findNavController().navigate(direction)
        }
        button21.setOnClickListener {
            val direction = PtsVehiclesDirections.actionPtsVehiclesToShowWebView("https://punjab.rescue1122.org/dashboard/vehicle/1311/edit")
            findNavController().navigate(direction)
        }
        button22.setOnClickListener {
            val direction = PtsVehiclesDirections.actionPtsVehiclesToShowWebView("https://punjab.rescue1122.org/dashboard/vehicle/1301/edit")
            findNavController().navigate(direction)
        }
    }
    override fun onStart() {
        super.onStart()
        dialog!!.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}