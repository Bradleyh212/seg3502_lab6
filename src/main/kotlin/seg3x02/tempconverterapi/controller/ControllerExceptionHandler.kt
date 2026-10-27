package seg3x02.tempconverterapi.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class ControllerExceptionHandler {
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleInvalidOperation(ex: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity.badRequest().body(ex.message ?: "Unable to process request")

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleInvalidNumber(ex: MethodArgumentTypeMismatchException): ResponseEntity<String> =
        ResponseEntity.badRequest().body("Invalid number for parameter ${ex.name}")
}
