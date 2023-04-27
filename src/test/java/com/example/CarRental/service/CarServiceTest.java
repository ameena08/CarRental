package com.example.CarRental.service;

import com.example.CarRental.entity.CarEntity;
import com.example.CarRental.repository.CarRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepo mockRepo;

    @InjectMocks
    private CarService carServiceUnderTest;

    @Test
    void testAddRegister() {
        // Setup
        final CarEntity car = new CarEntity("carId", "carName", "price", "brand", "color", 0, "availableDate",
                "status");
        final CarEntity expectedResult = new CarEntity("carId", "carName", "price", "brand", "color", 0,
                "availableDate", "status");

        // Configure CarRepo.save(...).
        final CarEntity carEntity = new CarEntity("carId", "carName", "price", "brand", "color", 0, "availableDate",
                "status");
        when(mockRepo.save(
                new CarEntity("carId", "carName", "price", "brand", "color", 0, "availableDate", "status")))
                .thenReturn(carEntity);

        // Run the test
        final CarEntity result = carServiceUnderTest.addRegister(car);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllCars() {
        // Setup
        final List<CarEntity> expectedResult = List.of(
                new CarEntity("carId", "carName", "price", "brand", "color", 0, "availableDate", "status"));

        // Configure CarRepo.findAll(...).
        final List<CarEntity> carEntities = List.of(
                new CarEntity("carId", "carName", "price", "brand", "color", 0, "availableDate", "status"));
        when(mockRepo.findAll()).thenReturn(carEntities);

        // Run the test
        final List<CarEntity> result = carServiceUnderTest.getAllCars();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllCars_CarRepoReturnsNoItems() {
        // Setup
        when(mockRepo.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<CarEntity> result = carServiceUnderTest.getAllCars();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetCarByDate() {
        // Setup
        final CarEntity expectedResult = new CarEntity("carId", "carName", "price", "brand", "color", 0,
                "availableDate", "status");

        // Configure CarRepo.findByavailableDate(...).
        final CarEntity carEntity = new CarEntity("carId", "carName", "price", "brand", "color", 0, "availableDate",
                "status");
        when(mockRepo.findByavailableDate("availableDate")).thenReturn(carEntity);

        // Run the test
        final CarEntity result = carServiceUnderTest.getCarByDate("availableDate");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
