package com.touk.parkingmanagement.parking

import com.touk.parkingmanagement.parking.dto.ParkingFeeDto
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class ParkingFeeCalculator {

    fun calculateParkingFee(parking: Parking, parkingRate: ParkingRate): ParkingFeeDto {
        val parkingTime = parking.getParkingDurationInHours()
        val firstHourRate = parkingRate.firstHourRate
        val secondHourRate = parkingRate.secondHourRate
        val fee = when {
            parkingTime == 0 -> BigDecimal.ZERO
            parkingTime == 1 -> firstHourRate
            parkingTime == 2 -> firstHourRate.add(secondHourRate)
            parkingTime > 2 -> firstHourRate.add(secondHourRate).add(calculateParkingFee(secondHourRate,
                    parkingRate.multiplier, parkingTime - 2))
            else -> BigDecimal.ZERO
        }
        return ParkingFeeDto(fee, parkingRate.currency.code)
    }

    fun calculateParkingFee(startingHourRate: BigDecimal, multiplier: BigDecimal, parkingTime: Int): BigDecimal {
        var currentHourFee = startingHourRate
        var fee = BigDecimal.ZERO
        for (i in 1..parkingTime) {
            currentHourFee = currentHourFee.multiply(multiplier).setScale(2, RoundingMode.DOWN)
            fee = fee.add(currentHourFee)
        }
        return fee
    }
}
