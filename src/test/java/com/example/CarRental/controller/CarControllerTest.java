package com.example.CarRental.controller;

import com.example.CarRental.entity.CarEntity;
import com.example.CarRental.service.CarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService mockCarService;

    @Test
    void testAddRegister() throws Exception {
        // Setup
        // Configure CarService.addRegister(...).
        final CarEntity carEntity = new CarEntity("carId", "carName", "price", "brand", "color", 0, "availableDate",
                "status");
        when(mockCarService.addRegister(
                new CarEntity("carId", "carName", "price", "brand", "color", 0, "availableDate", "status")))
                .thenReturn(carEntity);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/sharathcars/register")
                        .content(asJsonString(carEntity)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    private String asJsonString(CarEntity carEntity) {
        try{
            return new ObjectMapper().writeValueAsString(carEntity);
        }
        catch(JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }


    @Test
    void testGetAllCars() throws Exception {
        // Setup
        // Configure CarService.getAllCars(...).
        final List<CarEntity> carEntities = List.of(
                new CarEntity("carId", "carName", "price", "brand", "color", 0, "availableDate", "status"));
        when(mockCarService.getAllCars()).thenReturn(carEntities);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/sharathcars/cars")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetAllCars_CarServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockCarService.getAllCars()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/sharathcars/cars")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetCarByDate() throws Exception {
        // Setup
        // Configure CarService.getCarByDate(...).
        final CarEntity carEntity = new CarEntity("carId", "carName", "price", "brand", "color", 0, "availableDate",
                "status");
        when(mockCarService.getCarByDate("availableDate")).thenReturn(carEntity);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        get("/sharathcars/availability/{availableDate}", "availableDate")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
