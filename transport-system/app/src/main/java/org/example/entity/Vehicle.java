package org.example.entity;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.example.entity.enums.DrivingCategory;
import org.example.entity.enums.VehicleType;

@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Vehicle type cannot be null!")
    @Column(name = "vehicle_type", nullable = false)
    private VehicleType vehicleType;

    @NotBlank(message = "Manufacturer cannot be blank!")
    @Size(max = 100, message = "Manufacturer name cannot exceed 100 characters!")
    private String manifacture;

    @NotBlank(message = "Model cannot be blank!")
    @Size(max = 100, message = "Model name cannot exceed 100 characters!")
    private String model;

    @NotBlank(message = "License plate cannot be blank!")
    @Size(max = 20, message = "License plate cannot exceed 20 characters!")
    @Column(name = "license_plate", nullable = false, unique = true, length=20)
    private String licensePlate;

    @Min(value = 1800, message = "Production year must be at least 1800!")
    @Max(value = 2100, message = "Production year must be no later than 2100!")
    @Column(name = "production_year", nullable = false)
    private Integer productionYear;

    @PositiveOrZero(message = "Mileage must be zero or positive!")
    @Column(name = "mileage_km", nullable = false)
    private Double mileageKm;

    @Min(value = 1, message = "Horsepower must be at least 1!")
    @Max(value = 4000, message = "Horsepower must not exceed 4000!")
    @Column(name = "horse_power", nullable = false)
    private Integer horsePower;

    @PositiveOrZero(message = "Carrying amount must be zero or positive!")
    @Column(name = "carrying_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal carryingAmount;

    @PositiveOrZero(message = "Fuel consumption per 100km must be zero or positive!")
    @Column(name = "fuel_consumption_per_100km", nullable = false)
    private Double fuelConsumptionPer100Km;

    @PositiveOrZero(message = "Luggage capacity must be zero or positive!")
    @Column(name = "luggage_capacity_kg", nullable = false)
    private Double luggageCapacityKg;

    @Min(value = 1, message = "Seating capacity must be at least 1!")
    @Max(value = 100, message = "Seating capacity must not exceed 100!")
    @Column(name = "seating_capacity", nullable = false)
    private Integer seatingCapacity;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Required driving category cannot be null!")
    @Column(name = "required_driving_category", nullable = false)
    private DrivingCategory requiredDrivingCategory;

    @Column(name = "is_electric", nullable = false)
    private boolean isElectric;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    // Validation for production year (Custom Method)
    @AssertTrue(message = "Production year must be no later than the current year!")
    public boolean isProductionYearValid() {
        int currentYear = LocalDate.now().getYear();
        return productionYear <= currentYear;
    }

    // Constructors
    public Vehicle() {
    }

    public Vehicle(Company company, VehicleType vehicleType, String manifacture, String model, String licensePlate, 
                   Integer productionYear, Double mileageKm, Integer horsePower, BigDecimal carryingAmount, 
                   Double fuelConsumptionPer100Km, Double luggageCapacityKg, Integer seatingCapacity, 
                   DrivingCategory requiredDrivingCategory, boolean isElectric) {
        this.company = company;
        this.vehicleType = vehicleType;
        this.manifacture = manifacture;
        this.model = model;
        this.licensePlate = licensePlate;
        this.productionYear = productionYear;
        this.mileageKm = mileageKm;
        this.horsePower = horsePower;
        this.carryingAmount = carryingAmount;
        this.fuelConsumptionPer100Km = fuelConsumptionPer100Km;
        this.luggageCapacityKg = luggageCapacityKg;
        this.seatingCapacity = seatingCapacity;
        this.requiredDrivingCategory = requiredDrivingCategory;
        this.isElectric = isElectric;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getManifacture() {
        return manifacture;
    }

    public void setManifacture(String manifacture) {
        this.manifacture = manifacture;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public Double getMileageKm() {
        return mileageKm;
    }

    public void setMileageKm(Double mileageKm) {
        this.mileageKm = mileageKm;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }

    public BigDecimal getCarryingAmount() {
        return carryingAmount;
    }

    public void setCarryingAmount(BigDecimal carryingAmount) {
        this.carryingAmount = carryingAmount;
    }

    public Double getFuelConsumptionPer100Km() {
        return fuelConsumptionPer100Km;
    }

    public void setFuelConsumptionPer100Km(Double fuelConsumptionPer100Km) {
        this.fuelConsumptionPer100Km = fuelConsumptionPer100Km;
    }

    public Double getLuggageCapacityKg() {
        return luggageCapacityKg;
    }

    public void setLuggageCapacityKg(Double luggageCapacityKg) {
        this.luggageCapacityKg = luggageCapacityKg;
    }

    public Integer getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(Integer seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public DrivingCategory getRequiredDrivingCategory() {
        return requiredDrivingCategory;
    }

    public void setRequiredDrivingCategory(DrivingCategory requiredDrivingCategory) {
        this.requiredDrivingCategory = requiredDrivingCategory;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void softDelete() {
        this.isDeleted = true;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
            "id=" + id +
            ", company=" + (company != null ? company.getName() : "None") +
            ", vehicleType=" + vehicleType +
            ", manufacturer='" + manifacture + '\'' +
            ", model='" + model + '\'' +
            ", licensePlate='" + licensePlate + '\'' +
            ", productionYear=" + productionYear +
            ", mileageKm=" + mileageKm +
            ", horsePower=" + horsePower +
            ", carryingAmount=" + carryingAmount +
            ", fuelConsumptionPer100Km=" + fuelConsumptionPer100Km +
            ", luggageCapacityKg=" + luggageCapacityKg +
            ", seatingCapacity=" + seatingCapacity +
            ", requiredDrivingCategory=" + requiredDrivingCategory +
            ", isElectric=" + isElectric +
            ", isDeleted=" + isDeleted +
            '}';
    }
}