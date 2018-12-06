package com.touk.parkingmanagement.parking.exceptions

import com.touk.parkingmanagement.infrastructure.error.ParkingManagementException

class VehicleAlreadyParking(message: String) : ParkingManagementException(1004, message)
