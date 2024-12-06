package org.example.dto;

import java.math.BigDecimal;
import org.example.entity.enums.VehicleType;

public class VehicleDTO {
    private Long id;
    private Long companyId;
    private VehicleType vehicleType;
    private String manifacture;
    private String model;
    private String licensePlate;
    private Integer productionYear;
    private Double mileageKm;
    private Integer horsePower;
    private BigDecimal carryingAmount;
    private Double fuelConsumptionPer100Km;
    private Double luggageCapacityKg;
    private Integer seatingCapacity;
    private String requiredDrivingCategory;
    private boolean isElectric;
    private boolean isDeleted;

    public VehicleDTO() {}

    public VehicleDTO(Long id, Long companyId, VehicleType vehicleType, String manifacture, String model, String licensePlate, Integer productionYear, Double mileageKm, Integer horsePower, BigDecimal carryingAmount, Double fuelConsumptionPer100Km, Double luggageCapacityKg, Integer seatingCapacity, String requiredDrivingCategory, boolean isElectric, boolean isDeleted) {
        this.id = id;
        this.companyId = companyId;
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
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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

    public String getRequiredDrivingCategory() {
        return requiredDrivingCategory;
    }

    public void setRequiredDrivingCategory(String requiredDrivingCategory) {
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

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "VehicleDTO{" +
            "id=" + id +
            ", companyId=" + companyId +
            ", vehicleType=" + vehicleType +
            ", manufacture='" + manifacture + '\'' +
            ", model='" + model + '\'' +
            ", licensePlate='" + licensePlate + '\'' +
            ", productionYear=" + productionYear +
            ", mileageKm=" + mileageKm +
            ", horsePower=" + horsePower +
            ", carryingAmount=" + carryingAmount +
            ", fuelConsumptionPer100Km=" + fuelConsumptionPer100Km +
            ", luggageCapacityKg=" + luggageCapacityKg +
            ", seatingCapacity=" + seatingCapacity +
            ", requiredDrivingCategory='" + requiredDrivingCategory + '\'' +
            ", isElectric=" + isElectric +
            ", isDeleted=" + isDeleted +
            '}';
    }
}