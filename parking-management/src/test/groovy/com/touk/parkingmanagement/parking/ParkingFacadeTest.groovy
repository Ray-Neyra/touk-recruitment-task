package com.touk.parkingmanagement.parking

import com.touk.parkingmanagement.parking.exceptions.DriverTypeCodeNotFoundException
import com.touk.parkingmanagement.parking.exceptions.ParkingNotFoundException
import com.touk.parkingmanagement.parking.exceptions.ParkingRateNotFoundException
import com.touk.parkingmanagement.parking.exceptions.VehicleAlreadyParking
import com.touk.parkingmanagement.parking.exceptions.VehicleNotAssociatedWithUserException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Stepwise


@SpringBootTest
@ActiveProfiles(value = "test")
@Stepwise
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class ParkingFacadeTest extends Specification{

    @Autowired
    ParkingFacade parkingFacade

    def "Should start new parking for user with id: 1 and vehicle id: 1" () {

        when:
        def parkingId = parkingFacade.startParking(1, 1)

        then:
        parkingId == 51

    }

    def "Should not start new parking for user with id: 1 and vehicle id: 1" () {

        when:
        parkingFacade.startParking(1, 1)

        then:
        thrown(VehicleAlreadyParking)
    }

    def "Should not start new parking for user with id: 2 and vehicle id: 1" () {

        when:
        parkingFacade.startParking(1, 2)

        then:
        thrown(VehicleAlreadyParking)
    }

    def "Should not start new parking for user with id: 2 and vehicle id: 2" () {

        when:
        parkingFacade.startParking(2, 2)

        then:
        thrown(VehicleNotAssociatedWithUserException)
    }

    def "Should stop parking with id: 51 for user with id: 1 and vehicle id: 1" () {

        when:
        parkingFacade.stopParking(51, 1)

        then:
        noExceptionThrown()

    }

    def "Should not stop stopped parking with id: 51 for user with id: 1 and vehicle id: 1" () {

        when:
        parkingFacade.stopParking(51, 1)

        then:
        thrown(ParkingNotFoundException)

    }

    def "Should not stop not existing parking with id: 52 for user with id: 1 and vehicle id: 1" () {

        when:
        parkingFacade.stopParking(52, 1)

        then:
        thrown(ParkingNotFoundException)

    }

    def "Should get parking fee for parking with id: 1 in PLN" () {

        when:
        def parkingFee = parkingFacade.getParkingFee(1, CurrencyCode.PLN, 1)

        then:
        parkingFee.currencyCode == CurrencyCode.PLN
        parkingFee.fee == new BigDecimal("27.37")
    }

    def "Should get parking fee for parking with id: 1 in USD" () {

        when:
        def parkingFee = parkingFacade.getParkingFee(1, CurrencyCode.USD, 1)

        then:
        parkingFee.currencyCode == CurrencyCode.USD
        parkingFee.fee == new BigDecimal("6.82")
    }

    def "Should not get parking fee for not existing user" () {

        when:
        parkingFacade.getParkingFee(1, CurrencyCode.PLN, 3)

        then:
        thrown(DriverTypeCodeNotFoundException)
    }

    def "Should not get parking fee for user not connected to parking" () {

        when:
        parkingFacade.getParkingFee(1, CurrencyCode.PLN, 2)

        then:
        thrown(ParkingNotFoundException)
    }


    def "Should not get parking fee for not existing parking rate" () {

        when:
        parkingFacade.getParkingFee(2, CurrencyCode.USD, 2)

        then:
        thrown(ParkingRateNotFoundException)
    }

}
