package com.touk.parkingmanagement.driver

import com.touk.parkingmanagement.parking.ParkingFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles(value = "test")
class VehicleFacadeTest extends Specification {

    @Autowired
    VehicleFacade vehicleFacade

    @Autowired
    ParkingFacade parkingFacade

    def "Should return that parking meter is not running" () {

        when:
        def isParkingMeterRunning = vehicleFacade.isParkingMeterRunning("ABCDEFG")

        then:
        !isParkingMeterRunning

    }

    def "Should return that parking meter is running" () {

        given:
        def parkingId = parkingFacade.startParking(1, 1)

        when:
        def isParkingMeterRunning = vehicleFacade.isParkingMeterRunning("ABCDEFG")

        then:
        isParkingMeterRunning

        cleanup:
        parkingFacade.stopParking(parkingId, 1)

    }

    def "Should return that vehicle is associated with Driver" () {

        when:
        def isAssociatedWithDriver = vehicleFacade.isVehicleAssociatedWithDriver(1, 1)

        then:
        isAssociatedWithDriver

    }

    def "Should return that vehicle is not associated with Driver" () {

        when:
        def isAssociatedWithDriver = vehicleFacade.isVehicleAssociatedWithDriver(2, 2)

        then:
        !isAssociatedWithDriver

    }
}
