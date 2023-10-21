package com.microservices.store.productservice.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
  @NotNull
  @NotBlank
  private String name;
  private String description;
  @NotNull
  @DecimalMin(value = "1")
  private BigDecimal price;
  
}
