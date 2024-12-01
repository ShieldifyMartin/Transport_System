package org.example.entity;

import org.example.entity.enums.LoyaltyStatus;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotNull(message = "Customer since date cannot be null")
    private LocalDate customer_since;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @NotNull(message = "Loyalty status cannot be null")
    @Enumerated(EnumType.STRING)
    private LoyaltyStatus loyalty_status;

    @DecimalMin(value = "0.00", message = "Money spent must be greater than or equal to 0")
    @Digits(integer = 10, fraction = 2, message = "Money spent should have a maximum of 10 digits and 2 decimal places")
    private BigDecimal money_spent;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be a 10-digit number")
    private String phone;

    @NotNull(message = "Address cannot be null")
    @Size(min = 10, max = 255, message = "Address must be between 10 and 255 characters")
    private String address;

    // Constructors
    public Customer() {
    }

    public Customer(Long id, String name, String description, LocalDate customer_since, Company company,
                    LoyaltyStatus loyalty_status, BigDecimal money_spent, String phone, String address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.customer_since = customer_since;
        this.company = company;
        this.loyalty_status = loyalty_status;
        this.money_spent = money_spent;
        this.phone = phone;
        this.address = address;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCustomer_since() {
        return customer_since;
    }

    public void setCustomer_since(LocalDate customer_since) {
        this.customer_since = customer_since;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public LoyaltyStatus getLoyalty_status() {
        return loyalty_status;
    }

    public void setLoyalty_status(LoyaltyStatus loyalty_status) {
        this.loyalty_status = loyalty_status;
    }

    public BigDecimal getMoney_spent() {
        return money_spent;
    }

    public void setMoney_spent(BigDecimal money_spent) {
        this.money_spent = money_spent;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", customer_since=" + customer_since +
            ", company=" + company +
            ", loyalty_status=" + loyalty_status +
            ", money_spent=" + money_spent +
            ", phone='" + phone + '\'' +
            ", address='" + address + '\'' +
            '}';
    }
}