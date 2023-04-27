package com.example.CarRental.controller;

import com.example.CarRental.entity.CarEntity;
import com.example.CarRental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sharathcars")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping("/register")
    public CarEntity addRegister(@RequestBody CarEntity car){
        return carService.addRegister(car);
    }
    @GetMapping("/cars")
    public List<CarEntity> getAllCars(){
        return carService.getAllCars();
    }
    @GetMapping("/availability/{availableDate}")
    public CarEntity getCarByDate(@PathVariable String availableDate){
        return carService.getCarByDate(availableDate);
    }
}
