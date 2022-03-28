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

class MotorBikes : DialogFragment(R.layout.motor_bikes) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bnb1 = view.findViewById<AppCompatButton>(R.id.BNB01)
        val bnb2 = view.findViewById<AppCompatButton>(R.id.BNB02)
        val bnb3 = view.findViewById<AppCompatButton>(R.id.BNB03)
        val bnb4 = view.findViewById<AppCompatButton>(R.id.BNB04)
        val bnb5 = view.findViewById<AppCompatButton>(R.id.BNB05)
        val bnb6 = view.findViewById<AppCompatButton>(R.id.BNB06)
        val bnb7 = view.findViewById<AppCompatButton>(R.id.BNB07)
        val bnb8 = view.findViewById<AppCompatButton>(R.id.BNB08)
        val bnb9 = view.findViewById<AppCompatButton>(R.id.BNB09)
        val bnb10 = view.findViewById<AppCompatButton>(R.id.BNB10)
        val bnb11 = view.findViewById<AppCompatButton>(R.id.BNB11)
        val bnb12 = view.findViewById<AppCompatButton>(R.id.BNB12)
        val bnb13 = view.findViewById<AppCompatButton>(R.id.BNB13)
        val bnb14 = view.findViewById<AppCompatButton>(R.id.BNB14)

        bnb1.setOnClickListener {
            val directions = MotorBikesDirections.actionMotorBikeToShowWebView("https://punjab.rescue1122.org/dashboard/vehicle/1686/edit")
            findNavController().navigate(directions)
        }
        bnb2.setOnClickListener {
            val directions = MotorBikesDirections.actionMotorBikeToShowWebView("https://punjab.rescue1122.org/dashboard/vehicle/1675/edit")
            findNavController().navigate(directions)
        }
        bnb3.setOnClickListener {
            val directions = MotorBikesDirections.actionMotorBikeToShowWebView("https://punjab.rescue1122.org/dashboard/vehicle/1676/edit")
            findNavController().navigate(directions)
        }
        bnb4.setOnClickListener {
            val directions = MotorBikesDirections.actionMotorBikeToShowWebView("https://punjab.rescue1122.org/dashboard/vehicle/1690/edit")
            findNavController().navigate(directions)
        }
        bnb5.setOnClickListener {
            val directions = MotorBikesDirections.actionMotorBikeToShowWebView("https://punjab.rescue1122.org/dashboard/vehicle/1692/edit")
            findNavController().navigate(directions)
        }
        bnb6.setOnClickListener {
            val directions = MotorBikesDirections.actionMotorBikeToShowWebView("https://punjab.rescue1122.org/dashboard/vehicle/1693/edit")
            findNavController().navigate(directions)
        }
        bnb7.setOnClickListener {
            val directions = MotorBikesDirections.actionMotorBikeToShowWebView("https://punjab.rescue1122.org/dashboard/vehicle/1701/edit")
            findNavController().navigate(directions)
        }
        bnb8.setOnClickListener {
            val directions = MotorBikesDirections.actionMotorBikeToShowWebView("https://punjab.rescue1122.org/dashboard/vehicle/2422/edit")
            findNavController().navigate(directions)
        }
        bnb9.setOnClickListener {
            val directions = MotorBikesDirections.actionMotorBikeToShowWebView("https://punjab.rescue1122.org/dashboard/vehicle/2112/edit")
            findNavController().navigate(directions)
        }
        bnb10.setOnClickListener {
            val directions = MotorBikesDirections.actionMotorBikeToShowWebView("https://punjab.rescue1122.org/dashboard/vehicle/2506/edit")
            findNavController().navigate(directions)
        }
        bnb11.setOnClickListener {
            val directions = MotorBikesDirections.actionMotorBikeToShowWebView("https://punjab.rescue1122.org/dashboard/vehicle/2383/edit")
            findNavController().navigate(directions)
        }
        bnb12.setOnClickListener {
            val directions = MotorBikesDirections.actionMotorBikeToShowWebView("https://punjab.rescue1122.org/dashboard/vehicle/2043/edit")
            findNavController().navigate(directions)
        }
        bnb13.setOnClickListener {
            val directions = MotorBikesDirections.actionMotorBikeToShowWebView("https://punjab.rescue1122.org/dashboard/vehicle/2266/edit")
            findNavController().navigate(directions)
        }
        bnb14.setOnClickListener {
            val directions = MotorBikesDirections.actionMotorBikeToShowWebView("https://punjab.rescue1122.org/dashboard/vehicle/1691/edit")
            findNavController().navigate(directions)
        }
    }
    override fun onStart() {
        super.onStart()
        dialog!!.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}