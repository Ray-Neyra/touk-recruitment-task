package com.touk.parkingmanagement.parking.protocol

import java.math.BigDecimal

data class ParkingFeeResponse(val fee: BigDecimal, val currencyCode: String)
