package com.sajiiidali.rescue1122.vehicle.dashboard

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.util.*

class HomeFragment :Fragment(R.layout.homefragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar!!).setTitle(R.string.app_name)
        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar!!).show()

        val vStatus  = view.findViewById<AppCompatButton>(R.id.VehiclesStatus)
        val vehicles = view.findViewById<AppCompatButton>(R.id.Vehicles)
        val vDiagnoses = view.findViewById<AppCompatButton>(R.id.vehiclesDiagnoses)
        val employees  = view.findViewById<AppCompatButton>(R.id.Employees)
        val reports    = view.findViewById<AppCompatButton>(R.id.Reports)
        val dashBoard  = view.findViewById<AppCompatButton>(R.id.DashBoard)

        vStatus.setOnClickListener {
            val directions = HomeFragmentDirections.actionHomeFragmentToCheckVehicleStatus()
            findNavController().navigate(directions)
        }

        vehicles.setOnClickListener {
            val directions = HomeFragmentDirections.actionHomeFragmentToSelectVehiclesType()
            findNavController().navigate(directions)
        }

        vDiagnoses.setOnClickListener {
            val directions = HomeFragmentDirections.actionHomeFragmentToShowWebView2("https://punjab.rescue1122.org/dashboard/vehicle/diagnose")
            findNavController().navigate(directions)
        }
        employees.setOnClickListener {
            val directions = HomeFragmentDirections.actionHomeFragmentToSelectEmployees()
            findNavController().navigate(directions)
        }
        reports.setOnClickListener {
            val directions = HomeFragmentDirections.actionHomeFragmentToShowWebView2("https://punjab.rescue1122.org/dashboard/report/index")
            findNavController().navigate(directions)
        }
        dashBoard.setOnClickListener {
            val directions = HomeFragmentDirections.actionHomeFragmentToShowWebView2("https://punjab.rescue1122.org/dashboard")
            findNavController().navigate(directions)
        }
    }
}