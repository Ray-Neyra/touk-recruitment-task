package com.touk.parkingmanagement.driver

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles(value = "test")
class DriverFacadeTest extends Specification{

    @Autowired
    DriverFacade driverFacade

    def "Should return driver's DriverTypeCode" () {

        when:
        def driverTypeCode = driverFacade.getDriversDriverTypeCode(1)

        then:
        driverTypeCode == 1

    }

    def "Should not return driver's DriverTypeCode" () {

        when:
        def driverTypeCode = driverFacade.getDriversDriverTypeCode(3)

        then:
        driverTypeCode == null

    }

}
