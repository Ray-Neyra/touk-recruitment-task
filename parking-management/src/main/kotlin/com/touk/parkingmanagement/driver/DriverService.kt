package com.touk.parkingmanagement.driver

import com.touk.parkingmanagement.driver.model.Driver
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DriverService(
    private val driverRepository: DriverRepository
) {

    fun findDriverById(driverId: Long): Driver? {
        return driverRepository.findDriverById(driverId)
    }
}
