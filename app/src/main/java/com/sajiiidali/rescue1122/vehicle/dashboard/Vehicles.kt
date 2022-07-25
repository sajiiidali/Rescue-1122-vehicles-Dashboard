package com.sajiiidali.rescue1122.vehicle.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sajiiidali.rescue1122.vehicle.dashboard.databinding.VehiclesLayoutBinding

class Vehicles : Fragment() {

    private var _binding: VehiclesLayoutBinding? = null
    private val binding get() = _binding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = VehiclesLayoutBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding!!){
            vehicles.setOnClickListener {
                val direction = VehiclesDirections.actionVehicles3ToSelectVehiclesType()
                findNavController().navigate(direction)
            }
            vehiclesDiagnoses.setOnClickListener {
                val direction = VehiclesDirections.actionVehicles3ToShowWebView("https://punjab.rescue1122.org/dashboard/vehicle/diagnose")
                findNavController().navigate(direction)
            }
            VehiclesStatus.setOnClickListener {
                val direction = VehiclesDirections.actionVehicles3ToCheckVehicleStatus()
                findNavController().navigate(direction)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}