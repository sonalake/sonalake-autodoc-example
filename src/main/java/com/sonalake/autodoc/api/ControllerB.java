package com.sonalake.autodoc.api;

import com.sonalake.autodoc.model.TypeA;
import com.sonalake.autodoc.model.TypeB;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/endpoint-b")
@RequiredArgsConstructor
@Slf4j
@Api(tags = {"Section B"})
@Description("Some operations in section B")
public class ControllerB {

  @GetMapping
  @ApiOperation(value = "Gets a value", response = TypeA.class)
  public ResponseEntity<TypeB> getValue() {

    return ResponseEntity.ok(TypeB.builder()
      .fieldX("a value")
      .fieldZ("another value")
      .build()
    );
  }

  @PostMapping
  @ApiOperation(value = "send a value", response = TypeA.class)
  public ResponseEntity<TypeB> setValue(@RequestBody TypeB input) {

    return ResponseEntity.ok(
      TypeB.builder()
        .fieldX(" submitted: " + input.getFieldX())
        .fieldZ(" submitted: " + input.getFieldZ())
        .build()
    );
  }

}
