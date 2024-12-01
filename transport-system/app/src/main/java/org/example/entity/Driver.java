package org.example.entity;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import org.example.entity.enums.DrivingCategory;
import org.example.entity.enums.Position;

@Entity
@DiscriminatorValue("driver")
public class Driver extends Staff {
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @NotEmpty(message = "Driving categories cannot be empty!")
    private Set<DrivingCategory> drivingCategories;

    @Column(name = "total_km_driven_for_company")
    @PositiveOrZero(message = "Total kilometers driven for the company must be zero or positive!")
    private Long totalKmDrivenForCompany;

    @Column(name = "total_fines_and_sanctions")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total fines and sanctions must be greater than zero!")
    @PositiveOrZero(message = "Total fines and sanctions must be zero or positive!")
    private BigDecimal totalFinesAndSanctions;

    // Constructors
    public Driver() {
        super();
    }

    public Driver(String name, Position position, int age, BigDecimal salary, double yearlyBonusPercentage, 
                  LocalDate hiringDate, Company company, Set<DrivingCategory> drivingCategories, 
                  Long totalKmDrivenForCompany, BigDecimal totalFinesAndSanctions) {
        super(name, position, age, salary, yearlyBonusPercentage, hiringDate, company);
        this.drivingCategories = drivingCategories;
        this.totalKmDrivenForCompany = totalKmDrivenForCompany;
        this.totalFinesAndSanctions = totalFinesAndSanctions;
    }

    // Getters and Setters
    public Set<DrivingCategory> getDrivingCategories() {
        return drivingCategories;
    }

    public void setDrivingCategories(Set<DrivingCategory> drivingCategories) {
        this.drivingCategories = drivingCategories;
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
            ", drivingCategories=" + drivingCategories +
            ", totalKmDrivenForCompany=" + totalKmDrivenForCompany +
            ", totalFinesAndSanctions=" + totalFinesAndSanctions +
            '}';
    }
}
