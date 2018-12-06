package com.touk.parkingmanagement.payment

import com.touk.parkingmanagement.payment.dto.PaymentSummaryDto
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate

@Service
class PaymentFacade(
    private val paymentService: PaymentService
) {

    fun getPaymentsSummaryForGivenDate(givenDate: LocalDate): List<PaymentSummaryDto> {
        val payments = paymentService.getPaymentsForGivenDate(givenDate)
        return payments.groupingBy(Payment::currencyCode).aggregate { _, accumulator: BigDecimal?, element, first ->
            if (first)
                BigDecimal.ZERO.add(element.fee)
            else
                accumulator?.add(element.fee)
        }.map { it -> PaymentSummaryDto(it.value ?: BigDecimal.ZERO, it.key) }
    }
}
