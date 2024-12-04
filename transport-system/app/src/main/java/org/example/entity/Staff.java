package org.example.entity;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.example.entity.enums.Position;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Staff name cannot be blank!")
    @Size(max = 100, message = "Staff name must not exceed 100 characters!")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull(message = "Position cannot be null!")
    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false)
    private Position position;

    @Min(value = 16, message = "Staff age must be at least 16 years!")
    @Max(value = 100, message = "Staff age must not exceed 100 years!")
    @Column(name = "age", nullable = false)
    private int age;

    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0!")
    @Digits(integer = 10, fraction = 2, message = "Salary must be a valid monetary amount!")
    @Column(name = "salary", nullable = false, precision = 10, scale = 2)
    private BigDecimal salary;

    @DecimalMin(value = "0.0", message = "Yearly bonus percentage must not be negative!")
    @DecimalMax(value = "100.0", message = "Yearly bonus percentage must not exceed 100!")
    @Column(name = "yearly_bonus_percentage", nullable = false)
    private double yearlyBonusPercentage;

    @PastOrPresent(message = "Hiring date cannot be in the future!")
    @Column(name = "hiring_date", nullable = false)
    private LocalDate hiringDate;

    @NotNull(message = "Company cannot be null!")
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Email(message = "Email must be valid!")
    @NotBlank(message = "Email cannot be blank!")
    @Size(max = 100, message = "Email must not exceed 100 characters!")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalaryPayment> salaryPayments;

    // Constructors
    public Staff() {
    }

    public Staff(String name, Position position, int age, BigDecimal salary, double yearlyBonusPercentage, LocalDate hiringDate, Company company, String email) {
        this.name = name;
        this.position = position;
        this.age = age;
        this.salary = salary;
        this.yearlyBonusPercentage = yearlyBonusPercentage;
        this.hiringDate = hiringDate;
        this.company = company;
        this.email = email;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public double getYearlyBonusPercentage() {
        return yearlyBonusPercentage;
    }

    public void setYearlyBonusPercentage(double yearlyBonusPercentage) {
        this.yearlyBonusPercentage = yearlyBonusPercentage;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void softDelete() {
        this.isDeleted = true;
    }

    public List<SalaryPayment> getSalaryPayments() {
        return salaryPayments;
    }
    
    public void setSalaryPayments(List<SalaryPayment> salaryPayments) {
        this.salaryPayments = salaryPayments;
    }

    @Override
    public String toString() {
        return "Staff{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", position=" + position +
            ", age=" + age +
            ", salary=" + salary +
            ", yearlyBonusPercentage=" + yearlyBonusPercentage +
            ", hiringDate=" + hiringDate +
            ", company=" + (company != null ? company.getName() : "None") +
            ", email='" + email + '\'' +
            ", isDeleted=" + isDeleted +
            '}';
    }
}