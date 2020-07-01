package com.sonalake.autodoc.api;

import com.sonalake.autodoc.model.TypeB;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EndpointBTest extends BaseWebTest {

  @Test
  public void testGetValue() throws Exception {
    doGet("/api/endpoint-b").andExpect(status().isOk()).andReturn();
  }


  @Test
  public void testPostValue() throws Exception {
    TypeB content = TypeB.builder()
      .fieldX("sample X")
      .fieldZ("sample Y")
      .build();

    doPost("/api/endpoint-b", content).andExpect(status().isOk()).andReturn();
  }

}
