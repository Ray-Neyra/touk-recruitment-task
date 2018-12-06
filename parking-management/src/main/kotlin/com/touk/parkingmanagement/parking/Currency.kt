package com.touk.parkingmanagement.parking

import org.hibernate.annotations.Immutable
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "currencies")
@Immutable
class Currency (

    @Id
    val id: Long = 0L,
    val name: String = "",

    @Enumerated(value = EnumType.STRING)
    val code: CurrencyCode = CurrencyCode.PLN
)
