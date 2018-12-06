package com.touk.parkingmanagement.parking

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ParkingRateRepository : JpaRepository<ParkingRate, Long> {

    @Query("SELECT pr FROM ParkingRate pr left join pr.currency c where pr.driverTypeCode = :driverTypeCode " +
            "and pr.active = true and c.code = :currencyCode ")
    fun findActiveParkingRateByDriverTypeCodeAndCurrencyCode(
        @Param("currencyCode") currencyCode: CurrencyCode,
        @Param("driverTypeCode") driverTypeCode: Int
    ): ParkingRate?
}
