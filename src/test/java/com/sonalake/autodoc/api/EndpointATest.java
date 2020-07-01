package com.sonalake.autodoc.api;

import com.sonalake.autodoc.model.TypeA;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EndpointATest extends BaseWebTest {

  @Test
  public void testGetValue() throws Exception {
    doGet("/api/endpoint-a").andExpect(status().isOk()).andReturn();
  }


  @Test
  public void testPostValue() throws Exception {
    TypeA content = TypeA.builder()
      .fieldA("sample A")
      .fieldB(99)
      .build();

    doPost("/api/endpoint-a", content).andExpect(status().isOk()).andReturn();
  }

}
