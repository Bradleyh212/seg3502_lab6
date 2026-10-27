package seg3x02.tempconverterapi

import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorApiTests {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `arithmetic endpoints return numeric results`() {
        val cases = listOf(
            "add/2/3" to "5.0",
            "subtract/2/3" to "-1.0",
            "multiply/-2/3.5" to "-7.0",
            "divide/7/2" to "3.5",
            "divide/0/2" to "0.0"
        )
        for ((path, result) in cases) {
            mockMvc.perform(get("/calculator/$path"))
                .andExpect(status().isOk)
                .andExpect(content().string(result))
        }
    }

    @Test
    fun `division by zero returns a bad request`() {
        for (zero in listOf("0", "-0.0")) {
            mockMvc.perform(get("/calculator/divide/5/$zero"))
                .andExpect(status().isBadRequest)
                .andExpect(content().string("Cannot divide by zero"))
        }
    }

    @Test
    fun `nonnumeric input returns a bad request`() {
        mockMvc.perform(get("/calculator/add/abc/2"))
            .andExpect(status().isBadRequest)
            .andExpect(content().string("Invalid number for parameter nombre1"))
    }

    @Test
    fun `openapi documents all four operations and swagger is available`() {
        val result = mockMvc.perform(get("/v3/api-docs"))
            .andExpect(status().isOk)
        for (operation in listOf("add", "subtract", "multiply", "divide")) {
            result.andExpect(jsonPath("$.paths['/calculator/$operation/{nombre1}/{nombre2}'].get.responses['200']").exists())
                .andExpect(jsonPath("$.paths['/calculator/$operation/{nombre1}/{nombre2}'].get.responses['400'].content['text/plain'].schema.type").value("string"))
        }
        mockMvc.perform(get("/swagger-ui/index.html"))
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("Swagger UI")))
    }
}
