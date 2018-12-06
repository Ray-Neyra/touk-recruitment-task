package com.touk.parkingmanagement.payment

import com.touk.parkingmanagement.payment.protocol.PaymentSummaryResponse
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/api/payments")
class PaymentController(
    private val paymentFacade: PaymentFacade
) {

    @GetMapping
    fun getPaymentsSummaryForGivenDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) givenDate: LocalDate):
            List<PaymentSummaryResponse> {
        return paymentFacade.getPaymentsSummaryForGivenDate(givenDate).map { it -> PaymentSummaryResponse(it.fee, it.currency) }
    }
}
