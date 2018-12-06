package com.touk.parkingmanagement.driver.model

import com.touk.parkingmanagement.infrastructure.BaseEntity
import javax.persistence.Entity

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "vehicles")
class Vehicle(

    @Id
    @GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 50)
    val id: Long = 0L,
    val registrationNumber: String = ""

) : BaseEntity() {
    companion object {
        const val SEQUENCE_NAME = "vehicles_seq"
    }
}
