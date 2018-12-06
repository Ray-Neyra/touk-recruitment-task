package com.touk.parkingmanagement.parking

import com.touk.parkingmanagement.parking.dto.ParkingFeeDto
import com.touk.parkingmanagement.parking.exceptions.ParkingRateNotFoundException
import com.touk.parkingmanagement.parking.exceptions.ParkingNotFoundException
import com.touk.parkingmanagement.parking.exceptions.VehicleAlreadyParking
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ParkingService(
    private val parkingRepository: ParkingRepository,
    private val parkingRateRepository: ParkingRateRepository,
    private val parkingFeeCalculator: ParkingFeeCalculator
) {

    @Transactional(readOnly = true)
    fun getRunningParkingByVehicleId(vehicleId: Long): Parking? {
        return parkingRepository.getRunningParkingByVehicleId(vehicleId)
    }

    fun startParking(vehicleId: Long, driverId: Long): Long {
        if (parkingRepository.existsByVehicleIdAndEndDateNull(vehicleId)) {
            throw VehicleAlreadyParking("Vehicle with id: $vehicleId is parking right now")
        }
        return parkingRepository.save(Parking(vehicleId = vehicleId, driverId = driverId)).id
    }

    fun stopParking(parkingId: Long, driverId: Long) {
        val parkingRental = findRunningParking(parkingId, driverId)
        parkingRental.assignEndDateIfNotAssigned()
    }

    @Transactional(readOnly = true)
    fun getParkingFee(parkingId: Long, currencyCode: CurrencyCode, driverId: Long, driverTypeCode: Int): ParkingFeeDto {
        val parking = findParking(parkingId, driverId)
        val parkingRate = findParkingRate(currencyCode, driverTypeCode)
        return parkingFeeCalculator.calculateParkingFee(parking, parkingRate)
    }

    private fun findParkingRate(currencyCode: CurrencyCode, driverTypeCode: Int): ParkingRate {
        return parkingRateRepository.findActiveParkingRateByDriverTypeCodeAndCurrencyCode(currencyCode, driverTypeCode)
                ?: throw ParkingRateNotFoundException("Active parking rate for currency code: $currencyCode and driver type code: $driverTypeCode not found")
    }

    private fun findParking(parkingId: Long, driverId: Long): Parking {
        return parkingRepository.findByIdAndDriverId(parkingId, driverId)
                ?: throw ParkingNotFoundException("Parking with id: $parkingId and driver id: $driverId not found")
    }

    private fun findRunningParking(parkingId: Long, driverId: Long): Parking {
        return parkingRepository.findByIdAndDriverIdAndEndDateNull(parkingId, driverId)
                ?: throw ParkingNotFoundException("Running parking with id: $parkingId and driver id: $driverId not found")
    }
}
