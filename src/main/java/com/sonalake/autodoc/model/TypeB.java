package com.sonalake.autodoc.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class TypeB {
  private final String fieldX;
  private final String fieldZ;
}
