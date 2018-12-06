package com.touk.parkingmanagement.parking

import com.touk.parkingmanagement.infrastructure.BaseEntity
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "parkings")
class Parking(

    @Id
    @GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 50)
    var id: Long = 0L,
    var vehicleId: Long = 0L,
    var driverId: Long = 0L,
    var startDate: OffsetDateTime = OffsetDateTime.now(),
    var endDate: OffsetDateTime? = null,
    var paid: Boolean = false

) : BaseEntity() {
    companion object {
        private const val SEQUENCE_NAME = "parkings_seq"
        private const val MINUTES_IN_HOUR = 60
    }

    fun assignEndDateIfNotAssigned() {
        endDate = endDate ?: OffsetDateTime.now()
    }

    fun getParkingDurationInHours(): Int {
        val parkingDurationInMinutes = getParkingDurationMinutes()
        return convertToHours(parkingDurationInMinutes)
    }

    private fun getParkingDurationMinutes(): Long {
        return startDate.until(endDate ?: OffsetDateTime.now(), ChronoUnit.MINUTES)
    }

    private fun convertToHours(minutes: Long): Int {
        return (minutes / MINUTES_IN_HOUR + if (minutes % MINUTES_IN_HOUR == 0L) 0 else 1).toInt()
    }
}
