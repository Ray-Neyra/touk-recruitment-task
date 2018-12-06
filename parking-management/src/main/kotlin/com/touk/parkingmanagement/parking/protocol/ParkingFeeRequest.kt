package com.touk.parkingmanagement.parking.protocol

import com.touk.parkingmanagement.parking.CurrencyCode

data class ParkingFeeRequest(val driverId: Long, val currencyCode: CurrencyCode)
