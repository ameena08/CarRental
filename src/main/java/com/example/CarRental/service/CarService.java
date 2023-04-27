package com.example.CarRental.service;

import com.example.CarRental.entity.CarEntity;
import com.example.CarRental.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepo repo;
    public CarEntity addRegister(CarEntity car) {
        return repo.save(car);
    }

    public List<CarEntity> getAllCars() {
        return repo.findAll();
    }

    public CarEntity getCarByDate(String availableDate) {
        return repo.findByavailableDate(availableDate);
    }


}
