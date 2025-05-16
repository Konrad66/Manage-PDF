package konrad.lubaski.manage.pdf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration test that verifies POST /pdfs returns 201 Created,
 * sets the Location header, and echoes the newly‑created PDF object
 * in the response body.
 */
@SpringBootTest
@AutoConfigureMockMvc
class PdfControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreatePdf() throws Exception {
        // given
        String requestBody = """
                {
                  \"id\" : 202,
                  \"name\" : \"Python - For\"
                }
                """;

        // when & then
        mockMvc.perform(post("/pdfs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                // RESTful convention is the "Location" header, adjust the key if your API uses a custom name
                .andExpect(header().string("Location", "http://localhost:8080/pdfs/202"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(202))
                .andExpect(jsonPath("$.name").value("Python - For"));
    }
}