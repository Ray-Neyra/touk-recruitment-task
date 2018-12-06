package com.touk.parkingmanagement.driver

import com.touk.parkingmanagement.driver.protocol.VehicleParkingMeterStatusResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/vehicles")
class VehicleController(
    private val vehicleFacade: VehicleFacade
) {

    @GetMapping("/{registrationNumber}/parking-meter-status")
    fun isParkingMeterRunning(@PathVariable registrationNumber: String):
            ResponseEntity<VehicleParkingMeterStatusResponse> {
        return ResponseEntity
                .ok(VehicleParkingMeterStatusResponse(vehicleFacade.isParkingMeterRunning(registrationNumber)))
    }
}
