package com.burakozkan138;

import com.burakozkan138.enums.AccessType;
import com.burakozkan138.enums.FuelTypes;
import com.burakozkan138.enums.VehicleTypes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Vehicle {
  private int id;
  private String brand;
  private String model;
  private int year;
  private String color;
  private String plate;
  private VehicleTypes type; // SUV, SEDAN, HATCHBACK, COUPE, CONVERTIBLE, WAGON, VAN
  private FuelTypes fuelType; // gasoline, diesel, electric, etc.
  private int maxSpeed;
  private int maxPassengers;
  private double storageCapacity;
  private AccessType accessType;
  private int dailyPrice;
  private boolean isRented;

  public String toString() {
    return String.format("%d, %s, %s, %d, %s, %s, %s, %s, %d, %d, %s, %d", id, brand, model, year, color, plate, type,
        fuelType, maxSpeed, maxPassengers, storageCapacity, dailyPrice);
  }
}
