package com.touk.parkingmanagement.driver

import com.touk.parkingmanagement.parking.ParkingSearchFacade
import org.springframework.stereotype.Service

@Service
class VehicleFacade(
    private val vehicleService: VehicleService,
    private val parkingSearchFacade: ParkingSearchFacade
) {

    fun isParkingMeterRunning(registrationNumber: String): Boolean {
        return vehicleService.findVehicleByRegistrationNumber(registrationNumber)?.let { vehicle ->
            parkingSearchFacade.isParkingMeterRunningForVehicle(vehicle.id)
        } ?: false
    }

    fun isVehicleAssociatedWithDriver(vehicleId: Long, driverId: Long): Boolean {
        return vehicleService.isVehicleAssociatedWithDriver(vehicleId, driverId)
    }
}
