package com.touk.parkingmanagement.parking

import com.touk.parkingmanagement.parking.dto.ParkingFeeDto
import com.touk.parkingmanagement.parking.exceptions.DriverTypeCodeNotFoundException
import com.touk.parkingmanagement.parking.exceptions.VehicleNotAssociatedWithUserException
import com.touk.parkingmanagement.driver.DriverFacade
import com.touk.parkingmanagement.driver.VehicleFacade
import org.springframework.stereotype.Service

@Service
class ParkingFacade(
    private val parkingService: ParkingService,
    private val vehicleFacade: VehicleFacade,
    private val driverFacade: DriverFacade
) {

    fun startParking(vehicleId: Long, driverId: Long): Long {
        if (!vehicleFacade.isVehicleAssociatedWithDriver(vehicleId, driverId)) {
            throw VehicleNotAssociatedWithUserException("Vehicle with id: $vehicleId not associated with driver with id: $driverId")
        }
        return parkingService.startParking(vehicleId, driverId)
    }

    fun stopParking(parkingId: Long, driverId: Long) {
        parkingService.stopParking(parkingId, driverId)
    }

    fun getParkingFee(parkingId: Long, currencyCode: CurrencyCode, driverId: Long): ParkingFeeDto {
        val driverTypeCode = driverFacade.getDriversDriverTypeCode(driverId)
                ?: throw DriverTypeCodeNotFoundException("Drive type code for driver with id: $driverId not found")
        return parkingService.getParkingFee(parkingId, currencyCode, driverId, driverTypeCode)
    }
}
