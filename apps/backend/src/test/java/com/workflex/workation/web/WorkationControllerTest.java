package com.workflex.workation.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WorkationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsWorkationsImportedFromCsv() throws Exception {
        mockMvc.perform(get("/workflex/workation"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].workationId").value("w1"))
                .andExpect(jsonPath("$[0].employee").value("Steffen Jacobs"))
                .andExpect(jsonPath("$[0].origin").value("Germany"))
                .andExpect(jsonPath("$[0].destination").value("United States"))
                .andExpect(jsonPath("$[0].start").value("2024-01-02"))
                .andExpect(jsonPath("$[0].end").value("2024-12-31"))
                .andExpect(jsonPath("$[0].workingDays").value(65))
                .andExpect(jsonPath("$[0].risk").value("HIGH"))
                .andExpect(jsonPath("$[3].employee").value("Andre Fischer"))
                .andExpect(jsonPath("$[3].risk").value("LOW"))
                .andExpect(jsonPath("$[4].employee").value("Ayushi Singh"))
                .andExpect(jsonPath("$[4].risk").value("NO"));
    }
}
