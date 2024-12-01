package org.example.dto;

import java.math.BigDecimal;

public class VehicleDTO {
    private Long id;
    private Long companyId;
    private String type;
    private String manifacture;
    private String licensePlate;
    private Integer productionYear;
    private BigDecimal carryingAmount;
    private String requiredDrivingCategory;

    public VehicleDTO() {}

    public VehicleDTO(Long id, Long companyId, String type, String manifacture, String licensePlate, Integer productionYear, BigDecimal carryingAmount, String requiredDrivingCategory) {
        this.id = id;
        this.companyId = companyId;
        this.type = type;
        this.manifacture = manifacture;
        this.licensePlate = licensePlate;
        this.productionYear = productionYear;
        this.carryingAmount = carryingAmount;
        this.requiredDrivingCategory = requiredDrivingCategory;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "VehicleDTO{" +
            "id=" + id +
            ", companyId=" + companyId +
            ", type='" + type + '\'' +
            ", manufacture='" + manifacture + '\'' +
            ", licensePlate='" + licensePlate + '\'' +
            ", productionYear=" + productionYear +
            ", carryingAmount=" + carryingAmount +
            ", requiredDrivingCategory='" + requiredDrivingCategory + '\'' +
            '}';
    }
}