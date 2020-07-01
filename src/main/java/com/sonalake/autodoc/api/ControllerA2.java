package com.sonalake.autodoc.api;

import com.sonalake.autodoc.model.TypeA;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/endpoint-a")
@RequiredArgsConstructor
@Slf4j
@Api(tags = {"Section A"})
@Description("Some operations in section A")
public class ControllerA2 {

  @PostMapping
  @ApiOperation(value = "send a value", response = TypeA.class)
  public ResponseEntity<TypeA> setValue(@RequestBody TypeA input) {
    return ResponseEntity.ok(
      TypeA.builder()
        .fieldA(" submitted: " + input.getFieldA())
        .fieldB(-1 * input.getFieldB())
        .build()
    );
  }
}
