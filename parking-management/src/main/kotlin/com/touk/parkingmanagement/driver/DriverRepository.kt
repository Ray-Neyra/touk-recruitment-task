package com.touk.parkingmanagement.driver

import com.touk.parkingmanagement.driver.model.Driver
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DriverRepository : JpaRepository<Driver, Long> {

    fun findDriverById(id: Long): Driver?
}
