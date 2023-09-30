package com.mystore.productservice;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mystore.productservice.dtos.ProductRequest;
import com.mystore.productservice.repository.ProductRepository;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

  @Container
  static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private ProductRepository productRepository;

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
    dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
  }



	@Test
	void shouldSaveProduct() throws Exception {
    ProductRequest productRequest = getProductRequest();
    
    String productRequestString = objectMapper.writeValueAsString(productRequest);
    
    mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
    .contentType(MediaType.APPLICATION_JSON).content(productRequestString))
    .andExpect(MockMvcResultMatchers.status().isCreated());

    Assertions.assertEquals(1, productRepository.findAll().size());
  }

  private ProductRequest getProductRequest() {
  return ProductRequest.builder()
  .name("Macbook Pro 13")
  .description("32gb ram, 1tera ssd")
  .price(BigDecimal.valueOf(7500.55))
  .build();

  } 


}
