package com.touk.parkingmanagement.parking.dto

import com.touk.parkingmanagement.parking.CurrencyCode
import java.math.BigDecimal

data class ParkingFeeDto(val fee: BigDecimal, val currencyCode: CurrencyCode)
