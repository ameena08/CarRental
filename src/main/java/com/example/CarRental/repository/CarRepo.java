package com.example.CarRental.repository;

import com.example.CarRental.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends JpaRepository<CarEntity,String> {
    CarEntity findByavailableDate(String availableDate);
}
