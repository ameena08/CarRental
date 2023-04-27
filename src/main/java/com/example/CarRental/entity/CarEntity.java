package com.example.CarRental.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarEntity {
    @Id
    private String carId;
    private String carName;
    private String price;
    private String brand;
    private String color;
    private int noOfSeats;
    private String availableDate;
    private String status;
}
