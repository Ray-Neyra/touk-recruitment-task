package com.touk.parkingmanagement.payment

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface PaymentRepository : JpaRepository<Payment, Long> {

    fun findAllByPaymentDate(paymentDate: LocalDate): List<Payment>
}
