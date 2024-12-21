package org.example.dto;

import java.math.BigDecimal;

public class DriverDTO {
    private Long id;
    private String name;
    private String position;
    private BigDecimal salary;
    private Long companyId;
    private String drivingCategory; // Single driving category
    private boolean isDeleted;

    // Constructors
    public DriverDTO() {}

    public DriverDTO(Long id, String name, String position, BigDecimal salary, Long companyId, String drivingCategory, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.companyId = companyId;
        this.drivingCategory = drivingCategory;
        this.isDeleted = isDeleted;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getDrivingCategory() {
        return drivingCategory;
    }

    public void setDrivingCategory(String drivingCategory) {
        this.drivingCategory = drivingCategory;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "DriverDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", position='" + position + '\'' +
            ", salary=" + salary +
            ", companyId=" + companyId +
            ", drivingCategory='" + drivingCategory + '\'' +
            ", isDeleted=" + isDeleted +
            '}';
    }
}