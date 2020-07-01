package com.sonalake.autodoc.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static capital.scalable.restdocs.jackson.JacksonResultHandlers.prepareJackson;
import static capital.scalable.restdocs.response.ResponseModifyingPreprocessors.limitJsonArrayLength;
import static capital.scalable.restdocs.response.ResponseModifyingPreprocessors.replaceBinaryContent;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(RestDocumentationExtension.class)
public class BaseWebTest {

  protected MockMvc mockMvc;

  @Autowired
  protected ObjectMapper objectMapper;

  @BeforeEach
  public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {

    this.mockMvc = MockMvcBuilders
      .webAppContextSetup(webApplicationContext)
      .alwaysDo(prepareJackson(objectMapper))
      .alwaysDo(document("{class-name}/{method-name}",
        preprocessRequest(
          prettyPrint()
        ),
        preprocessResponse(
          replaceBinaryContent(),
          limitJsonArrayLength(objectMapper),
          prettyPrint())))
      .apply(documentationConfiguration(restDocumentation)
        .uris()
        .withScheme("https")
        .withHost("autodoc.sonalake.com")
        .withPort(443)
        .and().snippets().withEncoding(StandardCharsets.UTF_8.name())
      )
      .build();
  }

  @SneakyThrows
  protected ResultActions doGet(String url) {
    return mockMvc.perform(get(url)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .characterEncoding(StandardCharsets.UTF_8.name())
    );
  }

  @SneakyThrows
  protected <T> ResultActions doPost(String url, T body) {
    return mockMvc.perform(post(url)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .characterEncoding(StandardCharsets.UTF_8.name())
      .content(objectMapper.writeValueAsString(body))
    );
  }
}
