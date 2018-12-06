package com.touk.parkingmanagement.payment.protocol

import java.math.BigDecimal

data class PaymentSummaryResponse(val fee: BigDecimal, val currency: String)