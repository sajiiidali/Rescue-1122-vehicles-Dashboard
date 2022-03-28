package com.sajiiidali.rescue1122.vehicle.dashboard

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class SelectVehiclesType : Fragment(R.layout.select_type_vehicles) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val operational = view.findViewById<AppCompatButton>(R.id.Operational)
        val ptsVehiles  = view.findViewById<AppCompatButton>(R.id.ptsVehicles)
        val motorBike   = view.findViewById<AppCompatButton>(R.id.MotorBike)

        operational.setOnClickListener {
            val directions =  SelectVehiclesTypeDirections.actionSelectVehiclesTypeToShowOperationDialog()
            findNavController().navigate(directions)
        }
        ptsVehiles.setOnClickListener {
            val directions =  SelectVehiclesTypeDirections.actionSelectVehiclesTypeToPtsVehicles()
            findNavController().navigate(directions)
        }
        motorBike.setOnClickListener {
            val directions =  SelectVehiclesTypeDirections.actionSelectVehiclesTypeToMotorBike()
            findNavController().navigate(directions)
        }
    }

}