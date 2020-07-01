package com.sonalake.autodoc.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class TypeA {
  private final String fieldA;
  private final Integer fieldB;
}
