package com.touk.parkingmanagement.driver

import org.springframework.stereotype.Service

@Service
class DriverFacade(
    private val driverService: DriverService
) {

    fun getDriversDriverTypeCode(driverId: Long): Int? {
        return driverService.findDriverById(driverId)?.driverType?.code
    }
}
