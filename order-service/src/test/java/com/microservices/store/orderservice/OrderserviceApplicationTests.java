package com.microservices.store.orderservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.store.orderservice.dto.OrderLineItemsDto;
import com.microservices.store.orderservice.dto.OrderRequest;
import com.microservices.store.orderservice.repository.OrderRepository;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class OrderserviceApplicationTests {
	
	@Container
	static MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:5.7").withDatabaseName("testdb")
	.withUsername("test")
	.withPassword("test");


	@Autowired
  	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private OrderRepository orderRepository;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
		dynamicPropertyRegistry.add("spring.datasource.username", mySQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", mySQLContainer::getPassword);
	}


	@Test
	void shouldPlaceOrder() throws Exception {
		OrderRequest orderRequest = getOrderRequest();

		String orderString = objectMapper.writeValueAsString(orderRequest);
    
		mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
		.contentType(MediaType.APPLICATION_JSON).content(orderString))
		.andExpect(MockMvcResultMatchers.status().isCreated());

		Assertions.assertEquals(1, orderRepository.findAll().size());

	}


	private OrderRequest getOrderRequest() {
		OrderRequest orderRequest = new OrderRequest();
		List<OrderLineItemsDto> listOrderLineItemsDto = new ArrayList<OrderLineItemsDto>(Arrays.asList( new OrderLineItemsDto(1l, "Iphone_14", new BigDecimal(2300), 30)));
		orderRequest.setOrderLineItemsDtoList(listOrderLineItemsDto);
		return orderRequest;
	}


}
