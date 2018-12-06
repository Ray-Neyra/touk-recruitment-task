package com.touk.parkingmanagement.infrastructure.error

open class ParkingManagementException(val code: Int, message: String) : RuntimeException(message)
