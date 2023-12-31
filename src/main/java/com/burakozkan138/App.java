package com.burakozkan138;

import java.util.ArrayList;
import java.util.Scanner;

import com.burakozkan138.enums.AccessType;
import com.burakozkan138.enums.FuelTypes;
import com.burakozkan138.enums.VehicleTypes;

public class App {
  private ArrayList<Vehicle> vehicles = new ArrayList<>();
  private Scanner scanner;
  private AccessType userType;

  public App() {
    this.scanner = new Scanner(System.in);
    createVehicles();
  }

  public void run() {
    this.showLogin();
    this.showMainMenu();
  }

  private void showLogin() {
    this.clearConsole();
    System.out.println("Welcome to the Vehicle Rental System!");
    System.out.println("Please Login to Continue:");
    System.out.println("1. For User Login");
    System.out.println("2. For Company Login");
    System.out.println("3. For Exit");
    this.Login();
  }

  private void Login() {
    System.out.print("Please Enter Your Choice: ");
    String input = this.scanner.nextLine();

    try {
      int choice = Integer.parseInt(input);

      switch (choice) {
        case 1:
          this.userType = AccessType.Person;
          break;
        case 2:
          this.userType = AccessType.Company;
          break;
        case 3:
          this.exit();
          break;
        default:
          System.out.println("Invalid Input! Please Try Again");
          this.Login();
          break;
      }
    } catch (Exception e) {
      System.out.println("Invalid Input! Please Try Again");
      this.Login();
    }
  }

  private void showMainMenu() {
    this.clearConsole();
    System.out.println("Please Select an Option:");
    System.out.println("1. For Vehicle List");
    System.out.println("2. For Vehicle Search");
    System.out.println("3. For Exit");
    this.mainMenu();
  }

  private void mainMenu() {
    System.out.print("Please Enter Your Choice: ");
    String input = this.scanner.nextLine();

    try {
      int choice = Integer.parseInt(input);

      switch (choice) {
        case 1:
          this.showVehicleList(this.vehicles);
          break;
        case 2:
          // this.showVehicleSearch(); // TODO: implement search
          this.showVehicleList(this.vehicles);
          break;
        case 3:
          this.exit();
          break;
        default:
          System.out.println("Invalid Input! Please Try Again");
          this.mainMenu();
          break;
      }
    } catch (Exception e) {
      System.out.println("Invalid Input! Please Try Again");
      this.mainMenu();
    }
  }

  private void showVehicleList(ArrayList<Vehicle> vehicleList) {
    this.clearConsole();
    System.out.println("Vehicle List:");
    System.out.printf("| %-10s".repeat(12) + "| \n",
        "#ID", "Brand", "Model", "Year", "Color", "Plate", "Type", "Fuel", "Speed", "Passengers", "Storage", "Price");
    for (Vehicle vehicle : vehicleList) {
      if ((vehicle.getAccessType() == this.userType || vehicle.getAccessType() == AccessType.Everyone)
          && (!vehicle.isRented())) {
        System.out.printf(
            "| %-10s".repeat(12) + "| \n",
            (Object[]) vehicle.toString().split(","));

      }
    }
    this.selectVehicleById();
  }

  private void selectVehicleById() {
    System.out.print("Please Enter Vehicle ID: ");
    String input = this.scanner.nextLine();
    try {
      int choice = Integer.parseInt(input);

      Vehicle selectedVehicle = this.vehicles.stream().filter(vehicle -> vehicle.getId() == choice).findFirst().get();
      this.rentVehicle(selectedVehicle);
    } catch (Exception e) {
      System.out.println("Invalid Input! Please Try Again");
      this.selectVehicleById();
    }
  }

  private void rentVehicle(Vehicle vehicle) {
    System.out.println("Vehicle Selected: " + vehicle.getBrand() + " " + vehicle.getModel());
    System.out.print("Please Enter Rental Days: ");
    String input = this.scanner.nextLine();
    int totalPrice = 0;
    try {
      int days = Integer.parseInt(input);
      totalPrice = days * vehicle.getDailyPrice();
      System.out.println("Total Price: " + totalPrice);
    } catch (Exception e) {
      System.out.println("Invalid Input! Please Try Again");
      this.rentVehicle(vehicle);
    }

    System.out.println("Do You Want to Rent This Vehicle? (Y/N)");
    input = this.scanner.nextLine();

    if (input.equals("Y")) {
      System.out.println("Vehicle Rented Successfully!");
      System.out.println("Total Price: " + totalPrice);
      vehicle.setRented(true);
    } else {
      System.out.println("Vehicle Renting Canceled!");
    }
  }

  private void exit() {
    this.clearConsole();
    this.scanner.close();
    System.out.println("Thank you for using our system!");
    System.exit(0);
  }

  private void clearConsole() {
    System.out.print("\033[H\033[2J"); // from github-copilot
  }

  // create vehicles with github-copilot
  private void createVehicles() {

    // Vehicle 1
    this.vehicles.add(Vehicle
        .builder()
        .id(1)
        .brand("Ford")
        .model("Focus")
        .year(2015)
        .color("Black")
        .plate("34 ABC 34")
        .type(VehicleTypes.HATCHBACK)
        .fuelType(FuelTypes.GASOLINE)
        .maxSpeed(200)
        .maxPassengers(5)
        .storageCapacity(500)
        .accessType(AccessType.Everyone)
        .dailyPrice(200)
        .build());

    // Vehicle 2
    this.vehicles.add(Vehicle
        .builder()
        .id(2)
        .brand("Toyota")
        .model("Camry")
        .year(2020)
        .color("White")
        .plate("12 XYZ 78")
        .type(VehicleTypes.HATCHBACK)
        .fuelType(FuelTypes.HYBRID)
        .maxSpeed(180)
        .maxPassengers(5)
        .storageCapacity(400)
        .accessType(AccessType.Everyone)
        .dailyPrice(250)
        .build());

    // Vehicle 3
    this.vehicles.add(Vehicle
        .builder()
        .id(3)
        .brand("BMW")
        .model("X5")
        .year(2018)
        .color("Blue")
        .plate("56 DEF 90")
        .type(VehicleTypes.HATCHBACK)
        .fuelType(FuelTypes.DIESEL)
        .maxSpeed(220)
        .maxPassengers(7)
        .storageCapacity(600)
        .accessType(AccessType.Everyone)
        .dailyPrice(300)
        .build());

    // Vehicle 4
    this.vehicles.add(Vehicle
        .builder()
        .id(4)
        .brand("Mercedes")
        .model("C180")
        .year(2019)
        .color("Black")
        .plate("34 ABC 34")
        .type(VehicleTypes.SEDAN)
        .fuelType(FuelTypes.GASOLINE)
        .maxSpeed(200)
        .maxPassengers(5)
        .storageCapacity(500)
        .accessType(AccessType.Company)
        .dailyPrice(200)
        .build());

    // Vehicle 5
    this.vehicles.add(Vehicle
        .builder()
        .id(5)
        .brand("Mercedes")
        .model("E200")
        .year(2020)
        .color("White")
        .plate("12 XYZ 78")
        .type(VehicleTypes.SEDAN)
        .fuelType(FuelTypes.HYBRID)
        .maxSpeed(180)
        .maxPassengers(5)
        .storageCapacity(400)
        .accessType(AccessType.Company)
        .dailyPrice(250)
        .build());

    // Vehicle 6
    this.vehicles.add(Vehicle
        .builder()
        .id(6)
        .brand("BMW")
        .model("X7")
        .year(2018)
        .color("Blue")
        .plate("56 DEF 90")
        .type(VehicleTypes.SUV)
        .fuelType(FuelTypes.DIESEL)
        .maxSpeed(220)
        .maxPassengers(7)
        .storageCapacity(600)
        .accessType(AccessType.Company)
        .dailyPrice(300)
        .build());

    // Vehicle 7
    this.vehicles.add(Vehicle
        .builder()
        .id(7)
        .brand("Ford")
        .model("Focus")
        .year(2015)
        .color("Black")
        .plate("34 ABC 34")
        .type(VehicleTypes.SEDAN)
        .fuelType(FuelTypes.GASOLINE)
        .maxSpeed(200)
        .maxPassengers(5)
        .storageCapacity(500)
        .accessType(AccessType.Company)
        .dailyPrice(200)
        .build());

    // Vehicle 8
    this.vehicles.add(Vehicle
        .builder()
        .id(8)
        .brand("Toyota")
        .model("Camry")
        .year(2020)
        .color("White")
        .plate("12 XYZ 78")
        .type(VehicleTypes.SEDAN)
        .fuelType(FuelTypes.HYBRID)
        .maxSpeed(180)
        .maxPassengers(5)
        .storageCapacity(400)
        .accessType(AccessType.Company)
        .dailyPrice(250)
        .build());

    // Vehicle 9
    this.vehicles.add(Vehicle
        .builder()
        .id(9)
        .brand("BMW")
        .model("X5")
        .year(2018)
        .color("Blue")
        .plate("56 DEF 90")
        .type(VehicleTypes.SUV)
        .fuelType(FuelTypes.DIESEL)
        .maxSpeed(220)
        .maxPassengers(7)
        .storageCapacity(600)
        .accessType(AccessType.Company)
        .dailyPrice(300)
        .build());

    // Vehicle 10
    this.vehicles.add(Vehicle
        .builder()
        .id(10)
        .brand("Mercedes")
        .model("C180")
        .year(2019)
        .color("Black")
        .plate("34 ABC 34")
        .type(VehicleTypes.HATCHBACK)
        .fuelType(FuelTypes.GASOLINE)
        .maxSpeed(200)
        .maxPassengers(5)
        .storageCapacity(500)
        .accessType(AccessType.Everyone)
        .dailyPrice(200)
        .build());
  }
}