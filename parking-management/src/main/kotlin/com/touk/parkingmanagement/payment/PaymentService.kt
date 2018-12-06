package com.touk.parkingmanagement.payment

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class PaymentService(
    private val paymentRepository: PaymentRepository
) {

    @Transactional(readOnly = true)
    fun getPaymentsForGivenDate(givenDate: LocalDate): List<Payment> {
        return paymentRepository.findAllByPaymentDate(givenDate)
    }
}
