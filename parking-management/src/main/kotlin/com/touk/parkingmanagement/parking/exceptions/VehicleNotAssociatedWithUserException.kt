package com.touk.parkingmanagement.parking.exceptions

import com.touk.parkingmanagement.infrastructure.error.ParkingManagementException

class VehicleNotAssociatedWithUserException(message: String) : ParkingManagementException(1000, message)
