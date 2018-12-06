package com.touk.parkingmanagement.parking

import com.touk.parkingmanagement.parking.protocol.ParkingFeeRequest
import com.touk.parkingmanagement.parking.protocol.ParkingFeeResponse
import com.touk.parkingmanagement.parking.protocol.StartParkingRequest
import com.touk.parkingmanagement.parking.protocol.StopParkingRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/api/parkings")
class ParkingController(
    private val parkingFacade: ParkingFacade
) {

    @PostMapping
    fun startParking(@RequestBody @Valid startParkingRequest: StartParkingRequest): ResponseEntity<Any> {
        val rentalId = parkingFacade.startParking(startParkingRequest.vehicleId, startParkingRequest.driverId)
        return ResponseEntity.created(URI.create("/api/parkings/$rentalId")).build()
    }

    @PutMapping("/{parkingId}/stop")
    fun stopParking(@RequestBody @Valid stopParkingRequest: StopParkingRequest, @PathVariable parkingId: Long) {
        parkingFacade.stopParking(parkingId, stopParkingRequest.driverId)
    }

    @GetMapping("/{parkingId}/fee")
    fun getParkingFee(@RequestBody @Valid parkingFeeRequest: ParkingFeeRequest, @PathVariable parkingId: Long):
            ResponseEntity<ParkingFeeResponse> {
        val parkingFeeDto = parkingFacade.getParkingFee(parkingId, parkingFeeRequest.currencyCode, parkingFeeRequest.driverId)
        return ResponseEntity.ok(ParkingFeeResponse(parkingFeeDto.fee, parkingFeeDto.currencyCode.code))
    }
}
