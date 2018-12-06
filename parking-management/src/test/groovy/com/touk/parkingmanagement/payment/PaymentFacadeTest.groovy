package com.touk.parkingmanagement.payment

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import java.time.LocalDate

@SpringBootTest
@ActiveProfiles(value = "test")
class PaymentFacadeTest extends Specification {

    @Autowired
    PaymentFacade paymentFacade


    def "Should return payments summary list for 2018-12-05" () {

        given:
        def givenDay = LocalDate.of(2018,12,05)

        when:
        def paymentsSummaries = paymentFacade.getPaymentsSummaryForGivenDate(givenDay)

        then:
        paymentsSummaries.size() == 2
        paymentsSummaries.find{ it.currency == "PLN" && it.fee == new BigDecimal("42.24")} != null
        paymentsSummaries.find{ it.currency == "USD" && it.fee == new BigDecimal("1.50")} != null
    }

    def "Should return empty payments summary list for 2018-12-04" () {

        given:
        def givenDay = LocalDate.of(2018,12,04)

        when:
        def paymentsSummaries = paymentFacade.getPaymentsSummaryForGivenDate(givenDay)

        then:
        paymentsSummaries.size() == 0
    }

}
