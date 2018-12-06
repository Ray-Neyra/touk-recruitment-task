package com.touk.parkingmanagement.driver.model

import com.touk.parkingmanagement.infrastructure.BaseEntity
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "drivers")
class Driver(

    @Id
    @GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 50)
    val id: Long = 0L,
    var name: String = "",
    var surname: String = "",

    @Enumerated(value = EnumType.STRING)
    var role: Role = Role.DRIVER,

    @Enumerated(value = EnumType.STRING)
    var driverType: DriverType = DriverType.REGULAR,

    @ManyToMany(cascade = [CascadeType.PERSIST,
            CascadeType.MERGE])
    @JoinTable(
            name = "drivers_vehicles",
            joinColumns = [JoinColumn(name = "driver_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "vehicle_id", referencedColumnName = "id")]
    )
    val vehicles: MutableSet<Vehicle> = HashSet()

) : BaseEntity() {
    companion object {
        const val SEQUENCE_NAME = "drivers_seq"
    }
}
