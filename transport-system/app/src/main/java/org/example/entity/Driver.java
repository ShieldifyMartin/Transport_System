package org.example.entity;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.example.entity.enums.DrivingCategory;
import org.example.entity.enums.Position;

@Entity
@DiscriminatorValue("driver")
public class Driver extends Staff {

    @Column(name = "driving_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private DrivingCategory drivingCategory;

    @Column(name = "total_km_driven_for_company")
    @PositiveOrZero(message = "Total kilometers driven for the company must be zero or positive!")
    private Long totalKmDrivenForCompany;

    @Column(name = "total_fines_and_sanctions")
    @DecimalMin(value = "0.0", inclusive = true, message = "Total fines and sanctions must be zero or greater!")
    private BigDecimal totalFinesAndSanctions;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    // Constructors
    public Driver() {
        super();
    }

    public Driver(String name, Position position, int age, BigDecimal salary, double yearlyBonusPercentage, 
                  LocalDate hiringDate, Company company, String email, DrivingCategory drivingCategory, 
                  Long totalKmDrivenForCompany, BigDecimal totalFinesAndSanctions) {
        super(name, position, age, salary, yearlyBonusPercentage, hiringDate, company, email);
        this.drivingCategory = drivingCategory;
        this.totalKmDrivenForCompany = totalKmDrivenForCompany;
        this.totalFinesAndSanctions = totalFinesAndSanctions;
    }

    // Getters and Setters
    public DrivingCategory getDrivingCategory() {
        return drivingCategory;
    }

    public void setDrivingCategory(DrivingCategory drivingCategory) {
        this.drivingCategory = drivingCategory;
    }

    public Long getTotalKmDrivenForCompany() {
        return totalKmDrivenForCompany;
    }

    public void setTotalKmDrivenForCompany(Long totalKmDrivenForCompany) {
        this.totalKmDrivenForCompany = totalKmDrivenForCompany;
    }

    public BigDecimal getTotalFinesAndSanctions() {
        return totalFinesAndSanctions;
    }

    public void setTotalFinesAndSanctions(BigDecimal totalFinesAndSanctions) {
        this.totalFinesAndSanctions = totalFinesAndSanctions;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Driver{" +
            "id=" + getId() +
            ", name='" + getName() + '\'' +
            ", position=" + getPosition() +
            ", age=" + getAge() +
            ", salary=" + getSalary() +
            ", yearlyBonusPercentage=" + getYearlyBonusPercentage() +
            ", hiringDate=" + getHiringDate() +
            ", company=" + (getCompany() != null ? getCompany().getName() : "None") +
            ", email=" + getEmail() +
            ", drivingCategory=" + drivingCategory +
            ", totalKmDrivenForCompany=" + totalKmDrivenForCompany +
            ", totalFinesAndSanctions=" + totalFinesAndSanctions +
            '}';
    }
}
