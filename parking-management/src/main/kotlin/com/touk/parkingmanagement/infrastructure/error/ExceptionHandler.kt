package com.touk.parkingmanagement.infrastructure.error

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(ParkingManagementException::class)
    fun handleProductException(ex: ParkingManagementException): ResponseEntity<ErrorResponse> {
        logger.info("Handling ParkingManagementException. Message: {}", ex.message)
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse(ex.code, ex.message))
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ExceptionHandler::class.java)
    }
}
