package com.touk.parkingmanagement.parking.exceptions

import com.touk.parkingmanagement.infrastructure.error.ParkingManagementException

class DriverTypeCodeNotFoundException (message: String) : ParkingManagementException(1002, message)
