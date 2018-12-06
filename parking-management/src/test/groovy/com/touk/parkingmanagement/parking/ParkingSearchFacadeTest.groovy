package com.touk.parkingmanagement.parking

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles(value = "test")
class ParkingSearchFacadeTest extends Specification {

    @Autowired
    ParkingFacade parkingFacade

    @Autowired
    ParkingSearchFacade parkingSearchFacade

    def "Should return that parking is not running for vehicle" () {

        when:
        def isParkingRunning = parkingSearchFacade.isParkingMeterRunningForVehicle(1)

        then:
        !isParkingRunning
    }

    def "Should return that parking is running for vehicle" () {

        given:
        def parkingId = parkingFacade.startParking(1, 1)

        when:
        def isParkingRunning = parkingSearchFacade.isParkingMeterRunningForVehicle(1)

        then:
        isParkingRunning

        cleanup:
        parkingFacade.stopParking(parkingId, 1)

    }
}
