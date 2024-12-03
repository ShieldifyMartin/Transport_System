package org.example.entity;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import org.example.entity.Staff;
import org.example.entity.TransportOrder;
import org.example.entity.Vehicle;
@Entity
@Table(name="company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Company name cannot be blank!")
    @Size(max = 100, message = "Company name must not exceed 100 characters!")
    private String name;

    @NotBlank(message = "Address cannot be blank!")
    @Size(max = 255, message = "Address must not exceed 255 characters!")
    private String address;

    @PastOrPresent(message = "Foundation date cannot be in the future!")
    @Column(name = "foundation_date", nullable = false)
    private LocalDate foundationDate;

    @NotBlank(message = "VAT number cannot be blank!")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "VAT number must be alphanumeric!")
    @Column(unique = true, length = 15)
    private String VAT;

    @NotBlank(message = "Entity type cannot be blank!")
    @Size(max = 50, message = "Entity type must not exceed 50 characters!")
    private String entityType;

    @NotBlank(message = "Owners cannot be blank!")
    @Size(max = 1000, message = "Owners must not exceed 1000 characters!")
    @Column(name = "owners", length = 1000)
    private String owners;

    @NotNull(message = "Initial capital cannot be null!")
    @DecimalMin(value = "0.0", inclusive = false, message = "Initial capital must be greater than 0!")
    @Digits(integer = 15, fraction = 2, message = "Initial capital must be a valid monetary amount!")
    @Column(name = "initial_capital", precision = 15, scale = 2)
    private BigDecimal initialCapital;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "company")
    private Set<Staff> staff;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
    private Set<TransportOrder> transportOrders;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    // Constructors
    public Company() {
    }

    public Company(String name, String address, LocalDate foundationDate, String VAT, String entityType, 
                   String owners, BigDecimal initialCapital) {
        this.name = name;
        this.address = address;
        this.foundationDate = foundationDate;
        this.VAT = VAT;
        this.entityType = entityType;
        this.owners = owners;
        this.initialCapital = initialCapital;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(LocalDate foundationDate) {
        this.foundationDate = foundationDate;
    }

    public String getVAT() {
        return VAT;
    }

    public void setVAT(String VAT) {
        this.VAT = VAT;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getOwners() {
        return owners;
    }

    public void setOwners(String owners) {
        this.owners = owners;
    }

    public BigDecimal getInitialCapital() {
        return initialCapital;
    }

    public void setInitialCapital(BigDecimal initialCapital) {
        this.initialCapital = initialCapital;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Set<Staff> getStaff() {
        return staff;
    }

    public void setStaff(Set<Staff> staff) {
        this.staff = staff;
    }

    public Set<TransportOrder> getTransportOrders() {
        return transportOrders;
    }

    public void setTransportOrders(Set<TransportOrder> transportOrders) {
        this.transportOrders = transportOrders;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void softDelete() {
        this.isDeleted = true;
    }

    @Override
    public String toString() {
        return "Company{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            ", foundationDate=" + foundationDate +
            ", VAT='" + VAT + '\'' +
            ", entityType='" + entityType + '\'' +
            ", owners='" + owners + '\'' +
            ", initialCapital=" + initialCapital +
            ", isDeleted=" + isDeleted +
            '}';
    }
}