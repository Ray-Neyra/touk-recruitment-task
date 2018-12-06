package com.touk.parkingmanagement.infrastructure

import java.io.Serializable
import java.util.UUID
import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
class BaseEntity(
    @Column(unique = true)
    var uuid: String = UUID.randomUUID().toString()
) : Serializable {

    override fun hashCode(): Int {
        return uuid.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BaseEntity

        if (uuid != other.uuid) return false

        return true
    }
}
