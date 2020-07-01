package com.sonalake.autodoc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class GenerateDocumentationTest {

  @Autowired
  private MockMvc mockMvc;


  @Test
  public void createSpringfoxSwaggerJson() throws Exception {
    String outputDir = System.getProperty("io.springfox.staticdocs.outputDir", "./build/swagger");
    MvcResult mvcResult = this.mockMvc.perform(get("/v2/api-docs")
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andReturn();

    MockHttpServletResponse response = mvcResult.getResponse();
    String swaggerJson = response.getContentAsString(StandardCharsets.UTF_8);
    Files.createDirectories(Paths.get(outputDir));
    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputDir, "swagger.json"), StandardCharsets.UTF_8)) {
      writer.write(swaggerJson);
    }

  }
}
