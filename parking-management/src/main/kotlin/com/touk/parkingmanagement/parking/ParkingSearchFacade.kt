package com.touk.parkingmanagement.parking

import org.springframework.stereotype.Service

@Service
class ParkingSearchFacade(
    private val parkingService: ParkingService
) {
    fun isParkingMeterRunningForVehicle(vehicleId: Long): Boolean {
        return parkingService.getRunningParkingByVehicleId(vehicleId)?.let { true } ?: false
    }
}
