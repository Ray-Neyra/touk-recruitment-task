package com.touk.parkingmanagement.parking

import com.touk.parkingmanagement.infrastructure.BaseEntity
import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "parking_rates")
class ParkingRate (

    @Id
    @GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 50)
    val id: Long = 0L,
    var active: Boolean = true,
    val firstHourRate: BigDecimal = BigDecimal.ZERO,
    val secondHourRate: BigDecimal = BigDecimal.ZERO,
    val multiplier: BigDecimal = BigDecimal.ZERO,
    val driverTypeCode: Int,

    @ManyToOne
    @JoinColumn(name = "currency_id")
    val currency: Currency = Currency()

) : BaseEntity() {
    companion object {
        const val SEQUENCE_NAME = "parking_rates_seq"
    }
}
