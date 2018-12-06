package com.touk.parkingmanagement.payment

import com.touk.parkingmanagement.infrastructure.BaseEntity
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "payments")
class Payment(

    @Id
    @GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 50)
    val id: Long = 0L,
    val fee: BigDecimal = BigDecimal.ZERO,
    val currencyCode: String = "",
    val parkingId: Long = 0L,
    val paymentDate: LocalDate = LocalDate.now()

) : BaseEntity() {
    companion object {
        const val SEQUENCE_NAME = "payments_seq"
    }
}
