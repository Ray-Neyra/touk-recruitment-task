package com.touk.parkingmanagement.driver

import com.touk.parkingmanagement.driver.model.Vehicle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface VehicleRepository : JpaRepository<Vehicle, Long> {

    fun findByRegistrationNumber(registrationNumber: String): Vehicle?

    @Query("select count(d) > 0 from Driver d left join d.vehicles v where v.id = :vehicleId and d.id = :driverId")
    fun isVehicleAssociatedWithDriver(
        @Param("vehicleId") vehicleId: Long,
        @Param("driverId") driverId: Long
    ): Boolean
}
