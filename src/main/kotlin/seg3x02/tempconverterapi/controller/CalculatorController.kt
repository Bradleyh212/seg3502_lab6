package seg3x02.tempconverterapi.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("calculator")
@Tag(name = "Calculator", description = "Arithmetic operations on two numbers")
@ApiResponses(value = [
    ApiResponse(
        responseCode = "200",
        description = "Calculation result",
        content = [Content(mediaType = "application/json", schema = Schema(type = "number", format = "double"))]
    ),
    ApiResponse(
        responseCode = "400",
        description = "Invalid numeric parameter, or division by zero for the divide operation",
        content = [Content(mediaType = "text/plain", schema = Schema(type = "string"))]
    )
])
class CalculatorController {
    @GetMapping("/add/{nombre1}/{nombre2}")
    @Operation(summary = "Add two numbers")
    fun add(@PathVariable nombre1: Double, @PathVariable nombre2: Double): Double =
        nombre1 + nombre2

    @GetMapping("/subtract/{nombre1}/{nombre2}")
    @Operation(summary = "Subtract the second number from the first")
    fun subtract(@PathVariable nombre1: Double, @PathVariable nombre2: Double): Double =
        nombre1 - nombre2

    @GetMapping("/multiply/{nombre1}/{nombre2}")
    @Operation(summary = "Multiply two numbers")
    fun multiply(@PathVariable nombre1: Double, @PathVariable nombre2: Double): Double =
        nombre1 * nombre2

    @GetMapping("/divide/{nombre1}/{nombre2}")
    @Operation(summary = "Divide the first number by the second", description = "The divisor must be nonzero.")
    fun divide(@PathVariable nombre1: Double, @PathVariable nombre2: Double): Double {
        require(nombre2 != 0.0) { "Cannot divide by zero" }
        return nombre1 / nombre2
    }
}
