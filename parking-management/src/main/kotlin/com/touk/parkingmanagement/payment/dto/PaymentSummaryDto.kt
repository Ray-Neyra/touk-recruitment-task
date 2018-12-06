package com.touk.parkingmanagement.payment.dto

import java.math.BigDecimal

data class PaymentSummaryDto(val fee: BigDecimal, val currency: String)
