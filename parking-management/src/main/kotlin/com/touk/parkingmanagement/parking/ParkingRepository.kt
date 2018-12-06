package com.touk.parkingmanagement.parking

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ParkingRepository : JpaRepository<Parking, Long> {

    @Query("from Parking p where p.endDate is null and p.vehicleId = :vehicleId")
    fun getRunningParkingByVehicleId(@Param(value = "vehicleId") vehicleId: Long): Parking?

    fun findByIdAndDriverIdAndEndDateNull(id: Long, driverId: Long): Parking?

    fun findByIdAndDriverId(id: Long, driverId: Long): Parking?

    fun existsByVehicleIdAndEndDateNull(vehicleId: Long): Boolean
}
