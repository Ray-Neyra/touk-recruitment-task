package com.touk.parkingmanagement.driver

import com.touk.parkingmanagement.driver.model.Vehicle
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class VehicleService(
    val vehicleRepository: VehicleRepository
) {
    fun findVehicleByRegistrationNumber(registrationNumber: String): Vehicle? {
        return vehicleRepository.findByRegistrationNumber(registrationNumber)
    }

    fun isVehicleAssociatedWithDriver(vehicleId: Long, driverId: Long): Boolean {
        return vehicleRepository.isVehicleAssociatedWithDriver(vehicleId, driverId)
    }
}
