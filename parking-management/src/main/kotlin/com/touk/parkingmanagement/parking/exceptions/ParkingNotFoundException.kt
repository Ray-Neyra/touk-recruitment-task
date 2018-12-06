package com.touk.parkingmanagement.parking.exceptions

import com.touk.parkingmanagement.infrastructure.error.ParkingManagementException

class ParkingNotFoundException(message: String) : ParkingManagementException(1001, message)
