package org.example.dto;

import java.math.BigDecimal;
import org.example.entity.enums.VehicleType;

public class VehicleDTO {
    private Long id;
    private Long companyId;
    private VehicleType vehicleType;
    private String manifacture;
    private String licensePlate;
    private Integer productionYear;
    private BigDecimal carryingAmount;
    private String requiredDrivingCategory;
    private boolean isDeleted;

    public VehicleDTO() {}

    public VehicleDTO(Long id, Long companyId, VehicleType vehicleType, String manifacture, String licensePlate, Integer productionYear, BigDecimal carryingAmount, String requiredDrivingCategory, boolean isDeleted) {
        this.id = id;
        this.companyId = companyId;
        this.vehicleType = vehicleType;
        this.manifacture = manifacture;
        this.licensePlate = licensePlate;
        this.productionYear = productionYear;
        this.carryingAmount = carryingAmount;
        this.requiredDrivingCategory = requiredDrivingCategory;
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

    public BigDecimal getCarryingAmount() {
        return carryingAmount;
    }

    public void setCarryingAmount(BigDecimal carryingAmount) {
        this.carryingAmount = carryingAmount;
    }

    public String getRequiredDrivingCategory() {
        return requiredDrivingCategory;
    }

    public void setRequiredDrivingCategory(String requiredDrivingCategory) {
        this.requiredDrivingCategory = requiredDrivingCategory;
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
            ", licensePlate='" + licensePlate + '\'' +
            ", productionYear=" + productionYear +
            ", carryingAmount=" + carryingAmount +
            ", requiredDrivingCategory='" + requiredDrivingCategory + '\'' +
            ", isDeleted=" + isDeleted +
            '}';
    }
}