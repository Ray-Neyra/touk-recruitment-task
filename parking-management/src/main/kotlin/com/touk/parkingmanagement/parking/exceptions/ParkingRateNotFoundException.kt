package com.touk.parkingmanagement.parking.exceptions

import com.touk.parkingmanagement.infrastructure.error.ParkingManagementException

class ParkingRateNotFoundException(message: String) : ParkingManagementException(1003, message)
